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

import com.skilling.lms.curriculum_service.service.CompetenciaService;
import com.skilling.lms.shared.dtos.curriculum.request.CompetenciaRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.CompetenciaResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/competencias")
public class CompetenciaController {

    private final CompetenciaService competenciaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<CompetenciaResponseDTO> createCompetencia(@Valid @RequestBody CompetenciaRequestDTO requestDTO) {
        return competenciaService.createCompetencia(requestDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<CompetenciaResponseDTO> getAllCompetencias() {  
        return competenciaService.getAllCompetencias();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<CompetenciaResponseDTO> getCompetenciaById(@PathVariable UUID id) {
        return competenciaService.getCompetenciaById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Competencia no encontrada")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<CompetenciaResponseDTO> updateCompetencia(@PathVariable UUID id, @Valid @RequestBody CompetenciaRequestDTO requestDTO) {
        return competenciaService.updateCompetencia(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Competencia no encontrada para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCompetencia(@PathVariable UUID id) {
        return competenciaService.deleteCompetencia(id);
    }
}
