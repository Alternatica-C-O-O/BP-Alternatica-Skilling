package com.skilling.lms.users_service.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilling.lms.shared.dtos.users.request.UsuarioRequestDTO;
import com.skilling.lms.shared.dtos.users.response.RolResponseDTO;
import com.skilling.lms.shared.dtos.users.response.UsuarioResponseDTO;
import com.skilling.lms.shared.models.UsuarioRole;
import com.skilling.lms.users_service.domains.Usuario;
import com.skilling.lms.users_service.repositories.RolRepository;
import com.skilling.lms.users_service.repositories.UsuarioRepository;
import com.skilling.lms.users_service.repositories.UsuarioRoleRepository;
import com.skilling.lms.users_service.service.UsuarioService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioRoleRepository usuarioRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    private static final String USUARIO_SERVICE_CB = "usuarioServiceCB";

    @Override
    @CircuitBreaker(name = USUARIO_SERVICE_CB, fallbackMethod = "fallbackCreateUsuario")
    public Mono<UsuarioResponseDTO> createUsuario(UsuarioRequestDTO requestDTO) {
        return usuarioRepository.findByEmail(requestDTO.email())
                .flatMap(existingUser -> Mono.error(new RuntimeException("El email ya está registrado: " + requestDTO.email())))
                .then(Mono.defer(() -> {
                    Usuario usuario = Usuario.builder()
                            .nombre(requestDTO.nombre())
                            .apellido(requestDTO.apellido())
                            .email(requestDTO.email())
                            .claveHash(passwordEncoder.encode(requestDTO.clave()))
                            .fechaRegistro(LocalDateTime.now())
                            .estadoActivo(true)
                            .tipoUsuario(requestDTO.tipoUsuario())
                            .build();
                    return usuarioRepository.save(usuario);
                }))
                .cast(Usuario.class) 
                .flatMap(savedUsuario -> {
                    if (requestDTO.rolIds() != null && !requestDTO.rolIds().isEmpty()) {
                        return assignRolesToUsuarioInternal(savedUsuario.getId(), requestDTO.rolIds())
                                .then(getUsuarioWithRolesById(savedUsuario.getId()));
                    }
                    return Mono.just(toUsuarioResponseDTO(savedUsuario));
                });
    }

    @SuppressWarnings("unused")
    private Mono<UsuarioResponseDTO> fallbackCreateUsuario(UsuarioRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createUsuario activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Usuarios no está disponible o falló al crear el usuario."));
    }

    @Override
    @CircuitBreaker(name = USUARIO_SERVICE_CB, fallbackMethod = "fallbackGetUsuarioById")
    public Mono<UsuarioResponseDTO> getUsuarioById(UUID id) {
        return getUsuarioWithRolesById(id);
    }

    @SuppressWarnings("unused")
    private Mono<UsuarioResponseDTO> fallbackGetUsuarioById(UUID id, Throwable t) {
        log.error("Fallback para getUsuarioById activado para ID {}. Causa: {}", id, t.getMessage()); 
        return Mono.error(new RuntimeException("El servicio de Usuarios no está disponible o falló al obtener el usuario."));
    }

    @Override
    @CircuitBreaker(name = USUARIO_SERVICE_CB, fallbackMethod = "fallbackGetAllUsuarios")
    public Flux<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioRepository.findAllUsuariosWithRoles()
            .collectList()
            .flatMapMany(projections -> {
                return Flux.fromIterable(
                    projections.stream()
                        .collect(Collectors.groupingBy(UsuarioRepository.UsuarioWithRolesProjection::getId))
                        .values().stream()
                        .map(this::mapUsuarioWithRolesProjectionToDTO)
                        .collect(Collectors.toList())
                );
            });
    }

    @SuppressWarnings("unused")
    private Flux<UsuarioResponseDTO> fallbackGetAllUsuarios(Throwable t) {
        log.error("Fallback para getAllUsuarios activado. Causa: {}", t.getMessage()); 
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = USUARIO_SERVICE_CB, fallbackMethod = "fallbackUpdateUsuario")
    public Mono<UsuarioResponseDTO> updateUsuario(UUID id, UsuarioRequestDTO requestDTO) {
        return usuarioRepository.findById(id)
            .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado para actualizar con ID: " + id)))
            .flatMap(existingUsuario -> {
                if (!existingUsuario.getEmail().equals(requestDTO.email())) {
                    return usuarioRepository.findByEmail(requestDTO.email())
                        .flatMap(duplicateUser -> Mono.error(new RuntimeException("El nuevo email ya está registrado: " + requestDTO.email())))
                        .then(updateUsuarioFields(existingUsuario, requestDTO));
                } else {
                    return updateUsuarioFields(existingUsuario, requestDTO);
                }
            })
            .flatMap(updatedUsuario -> 
                removeAllRolesFromUsuarioInternal(updatedUsuario.getId())
                    .then(assignRolesToUsuarioInternal(updatedUsuario.getId(), requestDTO.rolIds()))
                    .then(getUsuarioWithRolesById(updatedUsuario.getId()))
            );
    }

    @SuppressWarnings("unused")
    private Mono<UsuarioResponseDTO> fallbackUpdateUsuario(UUID id, UsuarioRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateUsuario activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Usuarios no está disponible o falló al actualizar el usuario."));
    }

    @Override
    @CircuitBreaker(name = USUARIO_SERVICE_CB, fallbackMethod = "fallbackDeleteUsuario")
    public Mono<Void> deleteUsuario(UUID id) {
        return r2dbcEntityTemplate.delete(UsuarioRole.class)
            .matching(Query.query(Criteria.where("usuariosId").is(id)))
            .all()
            .then(usuarioRepository.deleteById(id));
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteUsuario(UUID id, Throwable t) {
        log.error("Fallback para deleteUsuario activado para ID {}. Causa: {}", id, t.getMessage()); 
        return Mono.error(new RuntimeException("El servicio de Usuarios no está disponible o falló al eliminar el usuario."));
    }
    
    @Override
    @CircuitBreaker(name = USUARIO_SERVICE_CB, fallbackMethod = "fallbackGetUsuarioByEmail")
    public Mono<UsuarioResponseDTO> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email)
            .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado con email: " + email)))
            .flatMap(usuario -> getUsuarioWithRolesById(usuario.getId()));
    }
    
    @SuppressWarnings("unused")
    private Mono<UsuarioResponseDTO> fallbackGetUsuarioByEmail(String email, Throwable t) {
        log.error("Fallback para getUsuarioByEmail activado para email {}. Causa: {}", email, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Usuarios no está disponible o falló al obtener el usuario por email."));
    }

    @Override
    @CircuitBreaker(name = USUARIO_SERVICE_CB, fallbackMethod = "fallbackAssignRolesToUsuario")
    public Mono<UsuarioResponseDTO> assignRolesToUsuario(UUID usuarioId, Set<UUID> rolIds) {
        return assignRolesToUsuarioInternal(usuarioId, rolIds)
            .then(getUsuarioWithRolesById(usuarioId));
    }

    @SuppressWarnings("unused")
    private Mono<UsuarioResponseDTO> fallbackAssignRolesToUsuario(UUID usuarioId, Set<UUID> rolIds, Throwable t) {
        log.error("Fallback para assignRolesToUsuario activado para Usuario ID {}. Causa: {}", usuarioId, t.getMessage()); 
        return Mono.error(new RuntimeException("El servicio de Usuarios no está disponible o falló al asignar roles."));
    }

    @Override
    @CircuitBreaker(name = USUARIO_SERVICE_CB, fallbackMethod = "fallbackRemoveRolesFromUsuario")
    public Mono<UsuarioResponseDTO> removeRolesFromUsuario(UUID usuarioId, Set<UUID> rolIds) {
        return Flux.fromIterable(rolIds)
            .flatMap(rolId ->
                r2dbcEntityTemplate.delete(UsuarioRole.class)
                    .matching(Query.query(Criteria.where("usuariosId").is(usuarioId)
                        .and("rolesId").is(rolId))).all()
                    .then(Mono.empty())
            )
            .then(getUsuarioWithRolesById(usuarioId));
    }

    @SuppressWarnings("unused")
    private Mono<UsuarioResponseDTO> fallbackRemoveRolesFromUsuario(UUID usuarioId, Set<UUID> rolIds, Throwable t) {
        log.error("Fallback para removeRolesFromUsuario activado para Usuario ID {}. Causa: {}", usuarioId, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Usuarios no está disponible o falló al remover roles."));
    }

    private Mono<Usuario> updateUsuarioFields(Usuario existingUsuario, UsuarioRequestDTO requestDTO) {
        existingUsuario.setNombre(requestDTO.nombre());
        existingUsuario.setApellido(requestDTO.apellido());
        existingUsuario.setEmail(requestDTO.email());
        existingUsuario.setTipoUsuario(requestDTO.tipoUsuario());
        if (requestDTO.clave() != null && !requestDTO.clave().isEmpty()) {
            existingUsuario.setClaveHash(passwordEncoder.encode(requestDTO.clave()));
        }
        return usuarioRepository.save(existingUsuario);
    }

    private Mono<Void> assignRolesToUsuarioInternal(UUID usuarioId, Set<UUID> rolIds) {
        if (rolIds == null || rolIds.isEmpty()) {
            return Mono.empty();
        }
        return Flux.fromIterable(rolIds)
            .flatMap(rolId -> rolRepository.findById(rolId)
                .switchIfEmpty(Mono.error(new RuntimeException("Rol no encontrado con ID: " + rolId)))
                .flatMap(rol -> {
                    UsuarioRole usuarioRole = new UsuarioRole(usuarioId, rolId);
                    return usuarioRoleRepository.save(usuarioRole).then();
                })
            ).then();
    }

    private Mono<Void> removeAllRolesFromUsuarioInternal(UUID usuarioId) {
        return r2dbcEntityTemplate.delete(UsuarioRole.class)
            .matching(Query.query(Criteria.where("usuariosId").is(usuarioId)))
            .all()
            .then();
    }

    private Mono<UsuarioResponseDTO> getUsuarioWithRolesById(UUID usuarioId) {
        return usuarioRepository.findUsuarioWithRolesById(usuarioId)
            .collectList()
            .flatMap(projections -> {
                if (projections.isEmpty()) {
                    return Mono.error(new RuntimeException("Usuario no encontrado con ID: " + usuarioId));
                }
                return Mono.just(mapUsuarioWithRolesProjectionToDTO(projections));
            });
    }

    private UsuarioResponseDTO mapUsuarioWithRolesProjectionToDTO(List<UsuarioRepository.UsuarioWithRolesProjection> projections) {
        UsuarioRepository.UsuarioWithRolesProjection primero = projections.get(0);
        Set<RolResponseDTO> roles = projections.stream()
            .filter(p -> p.getRolId() != null)
            .map(p -> new RolResponseDTO(
                p.getId(),
                p.getNombreRol(),
                p.getDescripcionRol(),
                Collections.emptySet()
            ))
            .collect(Collectors.toSet());
        return new UsuarioResponseDTO(
            primero.getId(),
            primero.getNombre(),
            primero.getApellido(),
            primero.getEmail(),
            primero.getFechaRegistro(),
            primero.getEstadoActivo(),
            primero.getTipoUsuario(),
            roles
        );
    }

    private UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getEmail(),
            usuario.getFechaRegistro(),
            usuario.getEstadoActivo(),
            usuario.getTipoUsuario(),
            Collections.emptySet()
        );
    }
}
