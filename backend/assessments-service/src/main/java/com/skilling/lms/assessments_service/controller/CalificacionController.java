package com.skilling.lms.assessments_service.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.skilling.lms.assessments_service.service.CalificacionService;
import com.skilling.lms.shared.dtos.assessments.request.CalificacionRequestDTO;
import com.skilling.lms.shared.dtos.assessments.response.CalificacionResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/calificaciones")
public class CalificacionController {

    private final CalificacionService calificacionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<CalificacionResponseDTO> createCalificacion(@Valid @RequestBody CalificacionRequestDTO requestDTO) {
        return calificacionService.createCalificacion(requestDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<CalificacionResponseDTO> getAllCalificaciones() {
        return calificacionService.getAllCalificaciones();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<CalificacionResponseDTO> getCalificacionById(@PathVariable UUID id) {
        return calificacionService.getCalificacionById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Calificacion no encontrada")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<CalificacionResponseDTO> updateCalificacion(@PathVariable UUID id, @Valid @RequestBody CalificacionRequestDTO requestDTO) {
        return calificacionService.updateCalificacion(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Calificacion no encontrada para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCalificacion(@PathVariable UUID id) {
        return calificacionService.deleteCalificacion(id);
    }
}
