package com.skilling.lms.users_service.service;

import java.util.Set;
import java.util.UUID;

import com.skilling.lms.shared.dtos.users.request.RolRequestDTO;
import com.skilling.lms.shared.dtos.users.response.RolResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolService {
    
    Mono<RolResponseDTO> createRol(RolRequestDTO requestDTO);
    Mono<RolResponseDTO> getRolById(UUID id);
    Flux<RolResponseDTO> getAllRoles();
    Mono<RolResponseDTO> updateRol(UUID id, RolRequestDTO requestDTO);
    Mono<Void> deleteRol(UUID id);

    // Métodos para manejar la relación N:M con Permisos
    Mono<RolResponseDTO> assignPermisosToRol(UUID rolId, Set<UUID> permisoIds);
    Mono<RolResponseDTO> removePermisosFromRol(UUID rolId, Set<UUID> permisoIds);
}
