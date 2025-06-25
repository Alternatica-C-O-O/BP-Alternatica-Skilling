package com.skilling.lms.users_service.service.impl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.skilling.lms.shared.dtos.users.request.PermisoRequestDTO;
import com.skilling.lms.shared.dtos.users.response.PermisoResponseDTO;
import com.skilling.lms.users_service.domains.Permiso;
import com.skilling.lms.users_service.repositories.PermisoRepository;
import com.skilling.lms.users_service.service.PermisoService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermisoServiceImpl implements PermisoService {

    private final PermisoRepository permisoRepository;
    
    private static final String PERMISO_SERVICE_CB = "permisoServiceCB";

    @Override
    @CircuitBreaker(name = PERMISO_SERVICE_CB, fallbackMethod = "fallbackCreatePermiso")
    public Mono<PermisoResponseDTO> createPermiso(PermisoRequestDTO requestDTO) {
        Permiso permiso = Permiso.builder()
                .nombrePermiso(requestDTO.nombrePermiso())
                .descripcion(requestDTO.descripcion())
                .build();
        return permisoRepository.save(permiso)
                .map(this::toPermisoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PermisoResponseDTO> fallbackCreatePermiso(PermisoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createPermiso activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Permisos no está disponible o falló al crear el permiso."));
    }

    @Override
    @CircuitBreaker(name = PERMISO_SERVICE_CB, fallbackMethod = "fallbackGetPermisoById")
    public Mono<PermisoResponseDTO> getPermisoById(UUID id) {
        return permisoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Permiso no encontrado con ID: " + id)))
                .map(this::toPermisoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PermisoResponseDTO> fallbackGetPermisoById(UUID id, Throwable t) {
        log.error("Fallback para getPermisoById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Permisos no está disponible o falló al obtener el permiso."));
    }

    @Override
    @CircuitBreaker(name = PERMISO_SERVICE_CB, fallbackMethod = "fallbackGetAllPermisos")
    public Flux<PermisoResponseDTO> getAllPermisos() {
        return permisoRepository.findAll()
                .map(this::toPermisoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<PermisoResponseDTO> fallbackGetAllPermisos(Throwable t) {
        log.error("Fallback para getAllPermisos activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = PERMISO_SERVICE_CB, fallbackMethod = "fallbackUpdatePermiso")
    public Mono<PermisoResponseDTO> updatePermiso(UUID id, PermisoRequestDTO requestDTO) {
        return permisoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Permiso no encontrado para actualizar con ID: " + id)))
                .flatMap(existingPermiso -> {
                    BeanUtils.copyProperties(requestDTO, existingPermiso, "id");
                    return permisoRepository.save(existingPermiso);
                })
                .map(this::toPermisoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PermisoResponseDTO> fallbackUpdatePermiso(UUID id, PermisoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updatePermiso activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Permisos no está disponible o falló al actualizar el permiso."));
    }

    @Override
    @CircuitBreaker(name = PERMISO_SERVICE_CB, fallbackMethod = "fallbackDeletePermiso")
    public Mono<Void> deletePermiso(UUID id) {
        return permisoRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeletePermiso(UUID id, Throwable t) {
        log.error("Fallback para deletePermiso activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Permisos no está disponible o falló al eliminar el permiso."));
    }

    private PermisoResponseDTO toPermisoResponseDTO(Permiso permiso) {
        return new PermisoResponseDTO(
            permiso.getId(),
            permiso.getNombrePermiso(),
            permiso.getDescripcion()
        );
    }
    
}
