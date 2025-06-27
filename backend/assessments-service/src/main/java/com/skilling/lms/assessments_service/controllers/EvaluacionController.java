package com.skilling.lms.assessments_service.controllers;

import com.skilling.lms.assessments_service.domains.Evaluacion;
import com.skilling.lms.assessments_service.services.EvaluacionService;
import com.skilling.lms.shared.models.enums.EvaluacionTipo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/evaluaciones")
public class EvaluacionController {

    private final EvaluacionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Evaluacion> create(@Valid @RequestBody Evaluacion evaluacion) {
        return service.save(evaluacion);
    }

    @GetMapping
    public Flux<Evaluacion> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Evaluacion> findById(@PathVariable UUID id) {
        return service.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping("/curso/{cursoId}")
    public Flux<Evaluacion> findByCurso(@PathVariable UUID cursoId) {
        return service.findByCursoOfertadoId(cursoId);
    }

    @GetMapping("/modulo/{moduloId}")
    public Flux<Evaluacion> findByModulo(@PathVariable UUID moduloId) {
        return service.findByModuloId(moduloId);
    }

    @GetMapping("/tipo/{tipo}")
    public Flux<Evaluacion> findByTipo(@PathVariable EvaluacionTipo tipo) {
        return service.findByTipoEvaluacion(tipo);
    }

    @GetMapping("/antes-de/{fecha}")
    public Flux<Evaluacion> findByFecha(@PathVariable String fecha) {
        return service.findByFechaVencimientoBefore(LocalDate.parse(fecha));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable UUID id) {
        return service.deleteById(id);
    }
}