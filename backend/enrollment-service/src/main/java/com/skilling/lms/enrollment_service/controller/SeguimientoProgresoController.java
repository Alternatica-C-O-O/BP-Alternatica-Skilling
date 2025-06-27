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

import com.skilling.lms.enrollment_service.service.SeguimientoProgresoService;
import com.skilling.lms.shared.dtos.enrollment.request.SeguimientoProgresoRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.SeguimientoProgresoResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seguimientos-progreso")
public class SeguimientoProgresoController {

    private final SeguimientoProgresoService seguimientoProgresoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<SeguimientoProgresoResponseDTO> createSeguimientoProgreso(@Valid @RequestBody SeguimientoProgresoRequestDTO requestDTO) {
        return seguimientoProgresoService.createSeguimientoProgreso(requestDTO);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<SeguimientoProgresoResponseDTO> getAllSeguimientosProgreso() {
        return seguimientoProgresoService.getAllSeguimientosProgreso();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<SeguimientoProgresoResponseDTO> getSeguimientoProgresoById(@PathVariable UUID id) {
        return seguimientoProgresoService.getSeguimientoProgresoById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Seguimiento de progreso no encontrado")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<SeguimientoProgresoResponseDTO> updateSeguimientoProgreso(@PathVariable UUID id, @Valid @RequestBody SeguimientoProgresoRequestDTO requestDTO) {
        return seguimientoProgresoService.updateSeguimientoProgreso(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Seguimiento de progreso no encontrado para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteSeguimientoProgreso(@PathVariable UUID id) {
        return seguimientoProgresoService.deleteSeguimientoProgreso(id);
    }
}
