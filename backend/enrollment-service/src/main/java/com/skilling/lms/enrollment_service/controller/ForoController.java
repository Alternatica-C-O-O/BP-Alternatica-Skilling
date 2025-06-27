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

import com.skilling.lms.enrollment_service.service.ForoService;
import com.skilling.lms.shared.dtos.enrollment.request.ForoRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.ForoResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/foros")
public class ForoController {

    private final ForoService foroService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<ForoResponseDTO> createForo(@Valid @RequestBody ForoRequestDTO requestDTO) {
        return foroService.createForo(requestDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<ForoResponseDTO> getAllForos() {
        return foroService.getAllForos();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<ForoResponseDTO> getForoById(@PathVariable UUID id) {
        return foroService.getForoById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Foro no encontrado")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<ForoResponseDTO> updateForo(@PathVariable UUID id, @Valid @RequestBody ForoRequestDTO requestDTO) {
        return foroService.updateForo(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Foro no encontrado para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteForo(@PathVariable UUID id) {
        return foroService.deleteForo(id);
    }
}
