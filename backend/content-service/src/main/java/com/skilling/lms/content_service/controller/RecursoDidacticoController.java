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

import com.skilling.lms.content_service.service.RecursoDidacticoService;
import com.skilling.lms.shared.dtos.content.request.RecursoDidacticoRequestDTO;
import com.skilling.lms.shared.dtos.content.response.RecursoDidacticoResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recursos-didacticos")
public class RecursoDidacticoController {

    private final RecursoDidacticoService recursoDidacticoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    Mono<RecursoDidacticoResponseDTO> createRecursoDidactico(@Valid @RequestBody RecursoDidacticoRequestDTO requestDTO) {
        return recursoDidacticoService.createRecursoDidactico(requestDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    Flux<RecursoDidacticoResponseDTO> getAllRecursosDidacticos() {
        return recursoDidacticoService.getAllRecursosDidacticos();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<RecursoDidacticoResponseDTO> getRecursoDidacticoById(@PathVariable UUID id) {
        return recursoDidacticoService.getRecursoDidacticoById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso didactico no encontrado")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<RecursoDidacticoResponseDTO> updateRecursoDidactico(@PathVariable UUID id, @Valid @RequestBody RecursoDidacticoRequestDTO requestDTO) {
        return recursoDidacticoService.updateRecursoDidactico(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso didactico no encontrado para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteRecursoDidactico(@PathVariable UUID id) {
        return recursoDidacticoService.deleteRecursoDidactico(id);
    }

}
