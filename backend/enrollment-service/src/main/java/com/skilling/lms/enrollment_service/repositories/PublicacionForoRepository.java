package com.skilling.lms.enrollment_service.repositories;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.enrollment_service.domains.PublicacionForo;

import reactor.core.publisher.Flux;

public interface PublicacionForoRepository extends ReactiveCrudRepository<PublicacionForo, UUID> {

    Flux<PublicacionForo> findByForoIdOrderByFechaPublicacionAsc(UUID foroId);
    Flux<PublicacionForo> findByUsuariosId(UUID usuarioId);
    Flux<PublicacionForo> findByPublicacionForoId(UUID publicacionForoId);
    Flux<PublicacionForo> findByForoIdAndPublicacionForoIdIsNullOrderByFechaPublicacionAsc(UUID foroId);
    Flux<PublicacionForo> findByFechaPublicacionBetween(LocalDateTime start, LocalDateTime end);
}
