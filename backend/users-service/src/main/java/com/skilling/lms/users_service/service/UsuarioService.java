package com.skilling.lms.users_service.service;

import java.util.Set;
import java.util.UUID;

import com.skilling.lms.shared.dtos.users.request.UsuarioRequestDTO;
import com.skilling.lms.shared.dtos.users.response.UsuarioResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioService {

    Mono<UsuarioResponseDTO> createUsuario(UsuarioRequestDTO requestDTO);
    Mono<UsuarioResponseDTO> getUsuarioById(UUID id);
    Flux<UsuarioResponseDTO> getAllUsuarios();
    Mono<UsuarioResponseDTO> updateUsuario(UUID id, UsuarioRequestDTO requestDTO);
    Mono<Void> deleteUsuario(UUID id);
    Mono<UsuarioResponseDTO> getUsuarioByEmail(String email); 

    // Métodos para manejar la relación N:M con Roles
    Mono<UsuarioResponseDTO> assignRolesToUsuario(UUID usuarioId, Set<UUID> rolIds);
    Mono<UsuarioResponseDTO> removeRolesFromUsuario(UUID usuarioId, Set<UUID> rolIds);
}
