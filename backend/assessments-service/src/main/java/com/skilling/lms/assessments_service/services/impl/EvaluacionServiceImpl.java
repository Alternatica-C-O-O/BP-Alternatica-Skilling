package com.skilling.lms.assessments_service.services.impl;

import com.skilling.lms.assessments_service.domains.Evaluacion;
import com.skilling.lms.assessments_service.repository.EvaluacionRepository;
import com.skilling.lms.assessments_service.services.EvaluacionService;
import com.skilling.lms.shared.models.enums.EvaluacionTipo;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EvaluacionServiceImpl implements EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;
    private static final String CB_NAME = "evaluacionServiceCB";

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackSave")
    public Mono<Evaluacion> save(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindAll")
    public Flux<Evaluacion> findAll() {
        return evaluacionRepository.findAll();
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindById")
    public Mono<Evaluacion> findById(UUID id) {
        return evaluacionRepository.findById(id);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindByCurso")
    public Flux<Evaluacion> findByCursoOfertadoId(UUID cursoOfertadoId) {
        return evaluacionRepository.findByCursoOfertadoId(cursoOfertadoId);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindByModulo")
    public Flux<Evaluacion> findByModuloId(UUID moduloId) {
        return evaluacionRepository.findByModuloId(moduloId);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindByTipo")
    public Flux<Evaluacion> findByTipoEvaluacion(EvaluacionTipo tipo) {
        return evaluacionRepository.findByTipoEvaluacion(tipo);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindByFecha")
    public Flux<Evaluacion> findByFechaVencimientoBefore(LocalDate date) {
        return evaluacionRepository.findByFechaVencimientoBefore(date);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackDelete")
    public Mono<Void> deleteById(UUID id) {
        return evaluacionRepository.deleteById(id);
    }

    @SuppressWarnings("unused")
    private Mono<Evaluacion> fallbackSave(Evaluacion evaluacion, Throwable t) {
        log.error("Fallback save Evaluacion: {}", t.getMessage());
        return Mono.error(new RuntimeException("No se pudo guardar la evaluación."));
    }

    @SuppressWarnings("unused")
    private Flux<Evaluacion> fallbackFindAll(Throwable t) {
        log.error("Fallback findAll Evaluacion: {}", t.getMessage());
        return Flux.empty();
    }

    @SuppressWarnings("unused")
    private Mono<Evaluacion> fallbackFindById(UUID id, Throwable t) {
        log.error("Fallback findById Evaluacion: {}", t.getMessage());
        return Mono.empty();
    }

    @SuppressWarnings("unused")
    private Flux<Evaluacion> fallbackFindByCurso(UUID id, Throwable t) {
        log.error("Fallback findByCursoOfertadoId: {}", t.getMessage());
        return Flux.empty();
    }

    @SuppressWarnings("unused")
    private Flux<Evaluacion> fallbackFindByModulo(UUID id, Throwable t) {
        log.error("Fallback findByModuloId: {}", t.getMessage());
        return Flux.empty();
    }

    @SuppressWarnings("unused")
    private Flux<Evaluacion> fallbackFindByTipo(EvaluacionTipo tipo, Throwable t) {
        log.error("Fallback findByTipoEvaluacion: {}", t.getMessage());
        return Flux.empty();
    }

    @SuppressWarnings("unused")
    private Flux<Evaluacion> fallbackFindByFecha(LocalDate date, Throwable t) {
        log.error("Fallback findByFechaVencimientoBefore: {}", t.getMessage());
        return Flux.empty();
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDelete(UUID id, Throwable t) {
        log.error("Fallback deleteById Evaluacion: {}", t.getMessage());
        return Mono.empty();
    }
}