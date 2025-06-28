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

import com.skilling.lms.curriculum_service.service.ModeloEducativoService;
import com.skilling.lms.shared.dtos.curriculum.request.ModeloEducativoRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.ModeloEducativoResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/modelos-educativos")
public class ModeloEducativoController {

    private final ModeloEducativoService modeloEducativoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<ModeloEducativoResponseDTO> createModeloEducativo(@Valid @RequestBody ModeloEducativoRequestDTO requestDTO) {
        return modeloEducativoService.createModeloEducativo(requestDTO);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<ModeloEducativoResponseDTO> getAllModelosEducativos() {
        return modeloEducativoService.getAllModelosEducativos();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<ModeloEducativoResponseDTO> getModeloEducativoById(@PathVariable UUID id) {
        return modeloEducativoService.getModeloEducativoById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo educativo no encontrado")));
    }
    
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<ModeloEducativoResponseDTO> updateModeloEducativo(@PathVariable UUID id, @Valid @RequestBody ModeloEducativoRequestDTO requestDTO) {
        return modeloEducativoService.updateModeloEducativo(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo educativo no encontrado para actualizar")));
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteModeloEducativo(@PathVariable UUID id) {
        return modeloEducativoService.deleteModeloEducativo(id);
    }
}
