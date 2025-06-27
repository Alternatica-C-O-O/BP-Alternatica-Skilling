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

import com.skilling.lms.resource_planning_service.service.EspacioFisicoService;
import com.skilling.lms.shared.dtos.resources_planning.request.EspacioFisicoRequestDTO;
import com.skilling.lms.shared.dtos.resources_planning.response.EspacioFisicoResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/espacios-fisicos")
public class EspacioFisicoController {

    private final EspacioFisicoService espacioFisicoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<EspacioFisicoResponseDTO> createEspacioFisico(@Valid @RequestBody EspacioFisicoRequestDTO requestDTO) {
        return espacioFisicoService.createEspacioFisico(requestDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<EspacioFisicoResponseDTO> getAllEspaciosFisicos() {
        return espacioFisicoService.getAllEspaciosFisicos();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<EspacioFisicoResponseDTO> getEspacioFisicoById(@PathVariable UUID id) {
        return espacioFisicoService.getEspacioFisicoById(id)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Espacio físico no encontrado")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<EspacioFisicoResponseDTO> updateEspacioFisico(@PathVariable UUID id,
        @Valid @RequestBody EspacioFisicoRequestDTO requestDTO) {
        return espacioFisicoService.updateEspacioFisico(id, requestDTO)
            .switchIfEmpty(Mono
                .error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Espacio físico no encontrado para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteEspacioFisico(@PathVariable("id") UUID id) {
        return espacioFisicoService.deleteEspacioFisico(id);
    }
}
