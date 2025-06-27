package com.skilling.lms.assessments_service.services;

import com.skilling.lms.assessments_service.domains.Evaluacion;
import com.skilling.lms.shared.models.enums.EvaluacionTipo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

public interface EvaluacionService {

    Mono<Evaluacion> save(Evaluacion evaluacion);
    Flux<Evaluacion> findAll();
    Mono<Evaluacion> findById(UUID id);
    Flux<Evaluacion> findByCursoOfertadoId(UUID cursoOfertadoId);
    Flux<Evaluacion> findByModuloId(UUID moduloId);
    Flux<Evaluacion> findByTipoEvaluacion(EvaluacionTipo tipo);
    Flux<Evaluacion> findByFechaVencimientoBefore(LocalDate date);
    Mono<Void> deleteById(UUID id);
}