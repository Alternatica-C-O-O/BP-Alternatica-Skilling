package com.skilling.lms.assessments_service.controllers;

import com.skilling.lms.assessments_service.domains.Calificacion;
import com.skilling.lms.assessments_service.services.CalificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/calificaciones")
public class CalificacionController {

    private final CalificacionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Calificacion> create(@Valid @RequestBody Calificacion calificacion) {
        return service.save(calificacion);
    }

    @GetMapping
    public Flux<Calificacion> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Calificacion> findById(@PathVariable UUID id) {
        return service.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping("/inscripcion/{inscripcionId}")
    public Flux<Calificacion> findByInscripcion(@PathVariable UUID inscripcionId) {
        return service.findByInscripcionId(inscripcionId);
    }

    @GetMapping("/evaluacion/{evaluacionId}")
    public Flux<Calificacion> findByEvaluacion(@PathVariable UUID evaluacionId) {
        return service.findByEvaluacionId(evaluacionId);
    }

    @GetMapping("/mayor-o-igual/{puntaje}")
    public Flux<Calificacion> findByPuntajeMayorIgual(@PathVariable Double puntaje) {
        return service.findByPuntajeObtenidoGreaterThanEqual(puntaje);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable UUID id) {
        return service.deleteById(id);
    }
}