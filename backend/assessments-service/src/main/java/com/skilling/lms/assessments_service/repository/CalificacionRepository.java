package com.skilling.lms.assessments_service.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.assessments_service.domains.Calificacion;

import reactor.core.publisher.Flux;

public interface CalificacionRepository extends ReactiveCrudRepository<CalificacionRepository, UUID> {

    Flux<Calificacion> findByInscripcionId(UUID inscripcionId);
    Flux<Calificacion> findByEvaluacionId(UUID evaluacionId);
    Flux<Calificacion> findByPuntajeObtenidoGreaterThanEqual(Double score);
}
