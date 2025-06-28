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

import com.skilling.lms.curriculum_service.service.ModuloService;
import com.skilling.lms.shared.dtos.curriculum.request.ModuloRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.ModuloResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/modulos")
public class ModuloController {

    private final ModuloService moduloService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<ModuloResponseDTO> createModulo(@Valid @RequestBody ModuloRequestDTO requestDTO) {
        return moduloService.createModulo(requestDTO);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<ModuloResponseDTO> getAllModulos() {
        return moduloService.getAllModulos();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<ModuloResponseDTO> getModuloById(@PathVariable UUID id) {
        return moduloService.getModuloById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Modulo no encontrado")));
    }
    
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<ModuloResponseDTO> updateModulo(@PathVariable UUID id, @Valid @RequestBody ModuloRequestDTO requestDTO) {
        return moduloService.getModuloById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Modulo no encontrado para actualizar")));
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteModulo(@PathVariable UUID id) {
        return moduloService.deleteModulo(id);
    }
}
