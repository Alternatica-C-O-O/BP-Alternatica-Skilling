package com.skilling.lms.resource_planning_service.controller;

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

import com.skilling.lms.resource_planning_service.service.AsignacionHorarioService;
import com.skilling.lms.shared.dtos.resources_planning.request.AsignacionHorarioRequestDTO;
import com.skilling.lms.shared.dtos.resources_planning.response.AsignacionHorarioResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/asignaciones-horario")
public class AsignacionHorarioController {

    private final AsignacionHorarioService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public Mono<AsignacionHorarioResponseDTO> create(@Valid @RequestBody AsignacionHorarioRequestDTO dto) {
        return service.createAsignacionHorario(dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<AsignacionHorarioResponseDTO> findAll() {
        return service.getAllAsignacionesHorario();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<AsignacionHorarioResponseDTO> findById(@PathVariable("id") UUID id) {
        return service.getAsignacionHorarioById(id).switchIfEmpty(
                Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignación no encontrada")));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public Mono<AsignacionHorarioResponseDTO> update(@PathVariable("id") UUID id,
            @Valid @RequestBody AsignacionHorarioRequestDTO dto) {
        return service.updateAsignacionHorario(id, dto).switchIfEmpty(Mono
                .error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignación no encontrada para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") UUID id) {
        return service.deleteAsignacionHorario(id);
    }
}
