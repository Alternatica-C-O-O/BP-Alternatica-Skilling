package com.skilling.lms.content_service.controller;

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

import com.skilling.lms.content_service.service.ContenidoModuloService;
import com.skilling.lms.shared.dtos.content.request.ContenidoModuloRequestDTO;
import com.skilling.lms.shared.dtos.content.response.ContenidoModuloResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contenidos-modulo")
public class ContenidoModuloController {

    private final ContenidoModuloService contenidoModuloService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<ContenidoModuloResponseDTO> createContenidoModulo(@Valid @RequestBody ContenidoModuloRequestDTO requestDTO) {
        return contenidoModuloService.createContenidoModulo(requestDTO);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<ContenidoModuloResponseDTO> getAllContenidosModulo() {
        return contenidoModuloService.getAllContenidosModulo();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<ContenidoModuloResponseDTO> getContenidoModuloById(@PathVariable UUID id) {
        return contenidoModuloService.getContenidoModuloById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Contenido modulo no encontrado")));
    }
    
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<ContenidoModuloResponseDTO> updateContenidoModulo(@PathVariable UUID id, @Valid @RequestBody ContenidoModuloRequestDTO requestDTO) {
        return contenidoModuloService.updateContenidoModulo(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Contenido modulo no encontrado para actualizar")));
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteContenidoModulo(@PathVariable UUID id) {
        return contenidoModuloService.deleteContenidoModulo(id);
    }
}
