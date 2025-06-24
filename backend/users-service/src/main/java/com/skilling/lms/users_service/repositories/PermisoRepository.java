package com.skilling.lms.users_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.users_service.domains.Permiso;

import reactor.core.publisher.Mono;

public interface PermisoRepository extends ReactiveCrudRepository<Permiso, UUID> {

    Mono<Permiso> findByNombrePermiso(String nombrePermiso);
}
