package com.skilling.lms.users_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.shared.models.RolPermiso;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolPermisoRepository extends ReactiveCrudRepository<RolPermiso, String> {
    
    Flux<RolPermiso> findByRolesId(UUID rolesId);
    Mono<RolPermiso> findByRolesIdAndPermisosId(UUID rolesId, UUID permisosId);
}
