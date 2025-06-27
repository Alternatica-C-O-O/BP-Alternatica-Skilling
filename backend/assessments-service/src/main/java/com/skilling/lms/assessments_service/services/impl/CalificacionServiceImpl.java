package com.skilling.lms.assessments_service.services.impl;

import com.skilling.lms.assessments_service.domains.Calificacion;
import com.skilling.lms.assessments_service.repository.CalificacionRepository;
import com.skilling.lms.assessments_service.services.CalificacionService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalificacionServiceImpl implements CalificacionService {

    private final CalificacionRepository calificacionRepository;
    private static final String CB_NAME = "calificacionServiceCB";

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackSave")
    public Mono<Calificacion> save(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindAll")
    public Flux<Calificacion> findAll() {
        return calificacionRepository.findAll();
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindById")
    public Mono<Calificacion> findById(UUID id) {
        return calificacionRepository.findById(id);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindByInscripcionId")
    public Flux<Calificacion> findByInscripcionId(UUID inscripcionId) {
        return calificacionRepository.findByInscripcionId(inscripcionId);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindByEvaluacionId")
    public Flux<Calificacion> findByEvaluacionId(UUID evaluacionId) {
        return calificacionRepository.findByEvaluacionId(evaluacionId);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackFindByScore")
    public Flux<Calificacion> findByPuntajeObtenidoGreaterThanEqual(Double score) {
        return calificacionRepository.findByPuntajeObtenidoGreaterThanEqual(score);
    }

    @Override
    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackDelete")
    public Mono<Void> deleteById(UUID id) {
        return calificacionRepository.deleteById(id);
    }

    @SuppressWarnings("unused")
    private Mono<Calificacion> fallbackSave(Calificacion calificacion, Throwable t) {
        log.error("Fallback save activado: {}", t.getMessage());
        return Mono.error(new RuntimeException("No se pudo guardar la calificación."));
    }

    @SuppressWarnings("unused")
    private Flux<Calificacion> fallbackFindAll(Throwable t) {
        log.error("Fallback findAll activado: {}", t.getMessage());
        return Flux.empty();
    }

    @SuppressWarnings("unused")
    private Mono<Calificacion> fallbackFindById(UUID id, Throwable t) {
        log.error("Fallback findById activado para ID {}: {}", id, t.getMessage());
        return Mono.empty();
    }

    @SuppressWarnings("unused")
    private Flux<Calificacion> fallbackFindByInscripcionId(UUID id, Throwable t) {
        log.error("Fallback findByInscripcionId activado: {}", t.getMessage());
        return Flux.empty();
    }

    @SuppressWarnings("unused")
    private Flux<Calificacion> fallbackFindByEvaluacionId(UUID id, Throwable t) {
        log.error("Fallback findByEvaluacionId activado: {}", t.getMessage());
        return Flux.empty();
    }

    @SuppressWarnings("unused")
    private Flux<Calificacion> fallbackFindByScore(Double score, Throwable t) {
        log.error("Fallback findByPuntajeObtenidoGreaterThanEqual activado: {}", t.getMessage());
        return Flux.empty();
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDelete(UUID id, Throwable t) {
        log.error("Fallback deleteById activado: {}", t.getMessage());
        return Mono.empty();
    }
}