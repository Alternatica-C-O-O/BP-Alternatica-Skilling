package com.skilling.lms.assessments_service.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.assessments_service.domains.Calificacion;
import com.skilling.lms.assessments_service.repository.CalificacionRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CalificacionService {

    private final CalificacionRepository calificacionRepository;

    public Mono<Calificacion> save(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    public Flux<Calificacion> findAll() {
        return calificacionRepository.findAll();
    }

    public Mono<Calificacion> findById(UUID id) {
        return calificacionRepository.findById(id);
    }

    public Flux<Calificacion> findByInscripcionId(UUID inscripcionId) {
        return calificacionRepository.findByInscripcionId(inscripcionId);
    }

    public Flux<Calificacion> findByEvaluacionId(UUID evaluacionId) {
        return calificacionRepository.findByEvaluacionId(evaluacionId);
    }

    public Flux<Calificacion> findByPuntajeObtenidoGreaterThanEqual(Double score) {
        return calificacionRepository.findByPuntajeObtenidoGreaterThanEqual(score);
    }

    public Mono<Void> deleteById(UUID id) {
        return calificacionRepository.deleteById(id);
    }
}