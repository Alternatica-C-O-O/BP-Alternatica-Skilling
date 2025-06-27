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

import com.skilling.lms.enrollment_service.service.PublicacionForoService;
import com.skilling.lms.shared.dtos.enrollment.request.PublicacionForoRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.PublicacionForoResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/publicaciones-foro")
public class PublicacionForoController {

    private final PublicacionForoService publicacionForoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PublicacionForoResponseDTO> createPublicacionForo(@Valid @RequestBody PublicacionForoRequestDTO requestDTO) {
        return publicacionForoService.createPublicacionForo(requestDTO);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<PublicacionForoResponseDTO> getAllPublicacionesForo() {
        return publicacionForoService.getAllPublicacionesForo();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<PublicacionForoResponseDTO> getPublicacionForoById(@PathVariable UUID id) {
        return publicacionForoService.getPublicacionForoById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Publicacion al Foro no encontrado")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PublicacionForoResponseDTO> updatePublicacionForo(@PathVariable UUID id, @Valid @RequestBody PublicacionForoRequestDTO requestDTO) {
        return publicacionForoService.updatePublicacionForo(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Publicacion al Foro no encontrado para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePublicacionForo(@PathVariable UUID id) {
        return publicacionForoService.deletePublicacionForo(id);
    }
}
