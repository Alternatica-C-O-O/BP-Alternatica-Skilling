package com.skilling.lms.reporting_service.repositories;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.reporting_service.domains.LogActividad;
import com.skilling.lms.shared.models.enums.EventoTipo;

import reactor.core.publisher.Flux;

public interface LogActividadRepository extends ReactiveCrudRepository<LogActividad, UUID> {

    Flux<LogActividad> findByUsuariosId(UUID usuariosId);
    Flux<LogActividad> findByTipoEvento(EventoTipo tipoEvento);
    Flux<LogActividad> findByFechaHoraBetween(LocalDateTime start, LocalDateTime end);
}
