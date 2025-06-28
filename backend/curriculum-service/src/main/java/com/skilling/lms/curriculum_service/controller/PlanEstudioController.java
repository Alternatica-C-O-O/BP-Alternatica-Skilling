package com.skilling.lms.curriculum_service.controller;

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

import com.skilling.lms.curriculum_service.service.PlanEstudioService;
import com.skilling.lms.shared.dtos.curriculum.request.PlanEstudioRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.PlanEstudioResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/planes-estudio")
public class PlanEstudioController {

    private final PlanEstudioService planEstudioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PlanEstudioResponseDTO> createPlanEstudio(@Valid @RequestBody PlanEstudioRequestDTO requestDTO) {
        return planEstudioService.createPlanEstudio(requestDTO);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<PlanEstudioResponseDTO> getAllPlanesEstudio() {
        return planEstudioService.getAllPlanesEstudio();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<PlanEstudioResponseDTO> getPlanEstudioById(@PathVariable UUID id) {
        return planEstudioService.getPlanEstudioById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan de estudio no encontrado")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PlanEstudioResponseDTO> updatePlanEstudio(@PathVariable UUID id, @Valid @RequestBody PlanEstudioRequestDTO requestDTO) {
        return planEstudioService.updatePlanEstudio(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan de estudio no encontrado para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePlanEstudio(@PathVariable UUID id) {
        return planEstudioService.deletePlanEstudio(id);
    }
}
