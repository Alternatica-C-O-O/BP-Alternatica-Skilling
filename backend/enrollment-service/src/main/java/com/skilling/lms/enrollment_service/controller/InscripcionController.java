package com.skilling.lms.enrollment_service.controller;

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

import com.skilling.lms.enrollment_service.service.InscripcionService;
import com.skilling.lms.shared.dtos.enrollment.request.InscripcionRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.InscripcionResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<InscripcionResponseDTO> createInscripcion(@Valid @RequestBody InscripcionRequestDTO requestDTO) {
        return inscripcionService.createInscripcion(requestDTO);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<InscripcionResponseDTO> getAllInscripciones() {
        return inscripcionService.getAllInscripciones();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<InscripcionResponseDTO> getInscripcionById(@PathVariable UUID id) {
        return inscripcionService.getInscripcionById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscripcion no encontrada")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<InscripcionResponseDTO> updateInscripcion(@PathVariable UUID id, @Valid @RequestBody InscripcionRequestDTO requestDTO) {
        return inscripcionService.updateInscripcion(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscripcion no encontrada para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteInscripcion(@PathVariable UUID id) {
        return inscripcionService.deleteInscripcion(id);
    }
}
