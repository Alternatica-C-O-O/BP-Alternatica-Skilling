package com.skilling.lms.users_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.users.request.PermisoRequestDTO;
import com.skilling.lms.shared.dtos.users.response.PermisoResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PermisoService {
    
    Mono<PermisoResponseDTO> createPermiso(PermisoRequestDTO requestDTO);
    Mono<PermisoResponseDTO> getPermisoById(UUID id);
    Flux<PermisoResponseDTO> getAllPermisos();
    Mono<PermisoResponseDTO> updatePermiso(UUID id, PermisoRequestDTO requestDTO);
    Mono<Void> deletePermiso(UUID id);
}
