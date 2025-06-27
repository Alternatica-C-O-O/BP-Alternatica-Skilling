package com.skilling.lms.assessments_service.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.assessments_service.domains.Evaluacion;
import com.skilling.lms.shared.models.enums.EvaluacionTipo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EvaluacionRepository extends ReactiveCrudRepository<Evaluacion, UUID> {

    Mono<Evaluacion> findByNombreEvaluacionAndCursoOfertadoId(String nombreEvaluacion, UUID cursoOfertadoId);
    Flux<Evaluacion> findByCursoOfertadoId(UUID cursoOfertadoId);
    Flux<Evaluacion> findByModuloId(UUID moduloId);
    Flux<Evaluacion> findByTipoEvaluacion(EvaluacionTipo tipoEvaluacion);
    Flux<Evaluacion> findByFechaVencimientoBefore(LocalDate date);
}
