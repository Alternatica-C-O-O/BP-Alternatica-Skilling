package com.skilling.lms.assessments_service.services;

import com.skilling.lms.assessments_service.domains.Calificacion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CalificacionService {

    Mono<Calificacion> save(Calificacion calificacion);
    Flux<Calificacion> findAll();
    Mono<Calificacion> findById(UUID id);
    Flux<Calificacion> findByInscripcionId(UUID inscripcionId);
    Flux<Calificacion> findByEvaluacionId(UUID evaluacionId);
    Flux<Calificacion> findByPuntajeObtenidoGreaterThanEqual(Double score);
    Mono<Void> deleteById(UUID id);
}