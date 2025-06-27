package com.skilling.lms.enrollment_service.repositories;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.enrollment_service.domains.Mensaje;

import reactor.core.publisher.Flux;

public interface MensajeRepository extends ReactiveCrudRepository<Mensaje, UUID> {

    Flux<Mensaje> findByForoId(UUID foroId);
    Flux<Mensaje> findByUsuariosId(UUID usuariosId);
    Flux<Mensaje> findByForoIdOrderByFechaEnvioDesc(UUID foroId);
    Flux<Mensaje> findByLeido(Boolean leido);
    Flux<Mensaje> findByFechaEnvioBetween(LocalDateTime start, LocalDateTime end);
}
