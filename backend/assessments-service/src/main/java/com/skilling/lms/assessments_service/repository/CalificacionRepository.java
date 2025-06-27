package com.skilling.lms.assessments_service.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.assessments_service.domains.Calificacion;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CalificacionRepository extends ReactiveCrudRepository<Calificacion, UUID> {

    Mono<Calificacion> findByInscripcionIdAndEvaluacionId(UUID inscripcionId, UUID evaluacionId);
    Flux<Calificacion> findByInscripcionId(UUID inscripcionId);
    Flux<Calificacion> findByEvaluacionId(UUID evaluacionId);
    Flux<Calificacion> findByPuntajeObtenidoGreaterThanEqual(Double score);
}
