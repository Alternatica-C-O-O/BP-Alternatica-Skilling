package com.skilling.lms.enrollment_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.enrollment_service.domains.PublicacionForo;

import reactor.core.publisher.Flux;

public interface PublicacionForoRepository extends ReactiveCrudRepository<PublicacionForoRepository, UUID> {

    Flux<PublicacionForo> findByForoId(UUID foroId);
    Flux<PublicacionForo> findByUsuariosId(UUID usuariosId);
    Flux<PublicacionForo> findByPublicacionForoId(UUID publicacionForoId);
}
