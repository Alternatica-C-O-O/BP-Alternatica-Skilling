package com.skilling.lms.assessments_service.services;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.assessments_service.domains.Evaluacion;
import com.skilling.lms.assessments_service.repository.EvaluacionRepository;
import com.skilling.lms.shared.models.enums.EvaluacionTipo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;

    public Mono<Evaluacion> save(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public Flux<Evaluacion> findAll() {
        return evaluacionRepository.findAll();
    }

    public Mono<Evaluacion> findById(UUID id) {
        return evaluacionRepository.findById(id);
    }

    public Flux<Evaluacion> findByCursoOfertadoId(UUID cursoOfertadoId) {
        return evaluacionRepository.findByCursoOfertadoId(cursoOfertadoId);
    }

    public Flux<Evaluacion> findByModuloId(UUID moduloId) {
        return evaluacionRepository.findByModuloId(moduloId);
    }

    public Flux<Evaluacion> findByTipoEvaluacion(EvaluacionTipo tipo) {
        return evaluacionRepository.findByTipoEvaluacion(tipo);
    }

    public Flux<Evaluacion> findByFechaVencimientoBefore(LocalDate date) {
        return evaluacionRepository.findByFechaVencimientoBefore(date);
    }

    public Mono<Void> deleteById(UUID id) {
        return evaluacionRepository.deleteById(id);
    }
}