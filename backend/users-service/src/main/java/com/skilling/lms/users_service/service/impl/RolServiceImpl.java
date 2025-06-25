package com.skilling.lms.users_service.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;

import com.skilling.lms.shared.dtos.users.request.RolRequestDTO;
import com.skilling.lms.shared.dtos.users.response.PermisoResponseDTO;
import com.skilling.lms.shared.dtos.users.response.RolResponseDTO;
import com.skilling.lms.shared.models.RolPermiso;
import com.skilling.lms.users_service.domains.Rol;
import com.skilling.lms.users_service.repositories.PermisoRepository;
import com.skilling.lms.users_service.repositories.RolPermisoRepository;
import com.skilling.lms.users_service.repositories.RolRepository;
import com.skilling.lms.users_service.service.RolService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final PermisoRepository permisoRepository;
    private final RolPermisoRepository rolPermisoRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    private static final String ROL_SERVICE_CB = "rolServiceCB";
    
    @Override
    @CircuitBreaker(name = ROL_SERVICE_CB, fallbackMethod = "fallbackCreateRol")
    public Mono<RolResponseDTO> createRol(RolRequestDTO requestDTO) {
        Rol rol = Rol.builder()
                .nombreRol(requestDTO.nombreRol())
                .descripcion(requestDTO.descripcion())
                .build();
        
        return rolRepository.save(rol)
                .flatMap(savedRol -> {
                    if (requestDTO.permisoIds() != null && !requestDTO.permisoIds().isEmpty()) {
                        return assignPermisosToRolInternal(savedRol.getId(), requestDTO.permisoIds())
                            .then(getRolWithPermisosById(savedRol.getId()));
                    }
                    return Mono.just(toRolResponseDTO(savedRol));
                });
    }

    @SuppressWarnings("unused")
    private Mono<RolResponseDTO> fallbackCreateRol(RolRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createRol activado. Causa: {}", t.getMessage()); 
        return Mono.error(new RuntimeException("El servicio de Roles no está disponible o falló al crear el rol."));
    }

    @Override
    @CircuitBreaker(name = ROL_SERVICE_CB, fallbackMethod = "fallbackGetRolById")
    public Mono<RolResponseDTO> getRolById(UUID id) {
        return getRolWithPermisosById(id);
    }

    @SuppressWarnings("unused")
    private Mono<RolResponseDTO> fallbackGetRolById(UUID id, Throwable t) {
        log.error("Fallback para getRolById activado para ID {}. Causa: {}", id, t.getMessage()); 
        return Mono.error(new RuntimeException("El servicio de Roles no está disponible o falló al obtener el rol."));
    }

    @Override
    @CircuitBreaker(name = ROL_SERVICE_CB, fallbackMethod = "fallbackGetAllRoles")
    public Flux<RolResponseDTO> getAllRoles() {
        return rolRepository.findAllRolesWithPermisos()
                .collectList()
                .flatMapMany(projections -> {
                    return Flux.fromIterable(
                        projections.stream()
                            .collect(Collectors.groupingBy(RolRepository.RolWithPermisosProjection::getId))
                            .values().stream()
                            .map(this::mapRolWithPermisosProjectionToDTO)
                            .collect(Collectors.toList())
                    );
                });
    }

    @SuppressWarnings("unused")
    private Flux<RolResponseDTO> fallbackGetAllRoles(Throwable t) {
        log.error("Fallback para getAllRoles activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = ROL_SERVICE_CB, fallbackMethod = "fallbackUpdateRol")
    public Mono<RolResponseDTO> updateRol(UUID id, RolRequestDTO requestDTO) {
        return rolRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Rol no encontrado para actualizar con ID: " + id)))
                .flatMap(existingRol -> {
                    BeanUtils.copyProperties(requestDTO, existingRol, "id");
                    return rolRepository.save(existingRol)
                            .flatMap(updateteRol -> {
                                return removeAllPermisosFromRolInternal(updateteRol.getId())
                                    .then(assignPermisosToRolInternal(updateteRol.getId(), requestDTO.permisoIds()))
                                    .then(getRolWithPermisosById(updateteRol.getId()));
                            });
                });
    }

    @SuppressWarnings("unused")
    private Mono<RolResponseDTO> fallbackUpdateRol(UUID id, RolRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateRol activado para ID {}. Causa: {}", id, t.getMessage()); 
        return Mono.error(new RuntimeException("El servicio de Roles no está disponible o falló al actualizar el rol."));
    }

    @Override
    @CircuitBreaker(name = ROL_SERVICE_CB, fallbackMethod = "fallbackDeleteRol")
    public Mono<Void> deleteRol(UUID id) {
        return rolRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Rol no encontrado para eliminar con ID: " + id)))
                .flatMap(rol -> 
                    r2dbcEntityTemplate.delete(RolPermiso.class)
                        .matching(Query.query(Criteria.where("rolesId").is(id)))
                        .all()
                        .then(rolRepository.delete(rol))
                );
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteRol(UUID id, Throwable t) {
        log.error("Fallback para deleteRol activado para ID {}. Causa: {}", id, t.getMessage()); 
        return Mono.error(new RuntimeException("El servicio de Roles no está disponible o falló al eliminar el rol."));
    }

    @Override
    @CircuitBreaker(name = ROL_SERVICE_CB, fallbackMethod = "fallbackAssignPermisosToRol")
    public Mono<RolResponseDTO> assignPermisosToRol(UUID rolId, Set<UUID> permisoIds) {
        return assignPermisosToRolInternal(rolId, permisoIds)
                .then(getRolWithPermisosById(rolId));
    }

    @SuppressWarnings("unused")
    private Mono<RolResponseDTO> fallbackAssignPermisosToRol(UUID rolId, Set<UUID> permisoIds, Throwable t) {
        log.error("Fallback para assignPermisosToRol activado para Rol ID {}. Causa: {}", rolId, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Roles no está disponible o falló al asignar permisos."));
    }

    @Override
    @CircuitBreaker(name = ROL_SERVICE_CB, fallbackMethod = "fallbackRemovePermisosFromRol")
    public Mono<RolResponseDTO> removePermisosFromRol(UUID rolId, Set<UUID> permisoIds) {
        return Flux.fromIterable(permisoIds)
                .flatMap(permisoId -> 
                    r2dbcEntityTemplate.delete(RolPermiso.class)
                        .matching(Query.query(Criteria.where("rolesId").is(rolId)
                                .and("permisosId").is(permisoId))).all()
                        .then(Mono.empty())
                )
                .then(getRolWithPermisosById(rolId));
    }

    @SuppressWarnings("unused")
    private Mono<RolResponseDTO> fallbackRemovePermisosFromRol(UUID rolId, Set<UUID> permisoIds, Throwable t) {
        log.error("Fallback para removePermisosFromRol activado para Rol ID {}. Causa: {}", rolId, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Roles no está disponible o falló al remover permisos."));
    }

    private Mono<Void> assignPermisosToRolInternal(UUID rolId, Set<UUID> permisoIds) {
        if (permisoIds == null || permisoIds.isEmpty()) {
            return Mono.empty();
        }
        return Flux.fromIterable(permisoIds)
                .flatMap(permisoId -> permisoRepository.findById(permisoId)
                    .switchIfEmpty(Mono.error(new RuntimeException("Permiso no encontrado con ID: " + permisoId)))
                    .flatMap(permiso -> {
                        RolPermiso rolPermiso = new RolPermiso(rolId, permisoId);
                        return rolPermisoRepository.save(rolPermiso).then();
                    })
                )
                .then();
    }

    private Mono<Void> removeAllPermisosFromRolInternal(UUID rolId) {
        return r2dbcEntityTemplate.delete(RolPermiso.class)
                .matching(Query.query(Criteria.where("rolesId").is(rolId)))
                .all()
                .then();
    }

    private Mono<RolResponseDTO> getRolWithPermisosById(UUID rolId) {
        return rolRepository.findRolWithPermisosById(rolId)
                .collectList()
                .flatMap(projections -> {
                    if (projections.isEmpty()) {
                        return Mono.error(new RuntimeException("Rol no encontrado con ID: " + rolId));
                    }
                    return Mono.just(mapRolWithPermisosProjectionToDTO(projections));
                });
    }

    private RolResponseDTO mapRolWithPermisosProjectionToDTO(List<RolRepository.RolWithPermisosProjection> projections) {
        RolRepository.RolWithPermisosProjection primero = projections.get(0);
        Set<PermisoResponseDTO> permisos = projections.stream()
            .filter(p -> p.getPermisoId() != null)
            .map(p -> new PermisoResponseDTO(
                p.getId(),
                p.getNombrePermiso(),
                p.getDescripcionPermiso()
            ))
            .collect(Collectors.toSet());
        
        return new RolResponseDTO(
            primero.getId(),
            primero.getNombreRol(),
            primero.getDescripcionRol(),
            permisos
        );
    }


    private RolResponseDTO toRolResponseDTO(Rol rol) {
        return new RolResponseDTO(
            rol.getId(),
            rol.getNombreRol(),
            rol.getDescripcion(),
            Collections.emptySet()
        );
    }
}
