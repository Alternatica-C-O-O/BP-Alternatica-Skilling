package com.skilling.lms.users_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.shared.models.UsuarioRole;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioRoleRepository extends ReactiveCrudRepository<UsuarioRole, String> {
    
    Flux<UsuarioRole> findByUsuariosId(UUID usuariosId);
    Mono<UsuarioRole> findByUsuariosIdAndRolesId(UUID usuariosId, UUID rolesId);
}
