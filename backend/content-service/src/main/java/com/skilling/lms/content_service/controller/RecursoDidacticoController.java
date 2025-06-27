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

import com.skilling.lms.shared.dtos.create.RecursoDidacticoCreateRequestDTO;
import com.skilling.lms.shared.dtos.recurso_didactico.request.RecursoDidacticoRequestDTO;
import com.skilling.lms.shared.dtos.recurso_didactico.response.RecursoDidacticoResponseDTO;
import com.skilling.lms.content_service.service.RecursoDidacticoService;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recursos-didacticos")
public class RecursoDidacticoController {

    private final RecursoDidacticoService recursoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<RecursoDidacticoResponseDTO> createRecurso(@Valid @RequestBody RecursoDidacticoCreateRequestDTO requestDTO) {
        return recursoService.createRecurso(requestDTO)
                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al crear el recurso: " + e.getMessage())));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<RecursoDidacticoResponseDTO> getAllRecursos() {
        return recursoService.getAllRecursos()
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron recursos")));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<RecursoDidacticoResponseDTO> getRecursoById(@PathVariable("id") UUID id) {
        return recursoService.getRecursoById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso no encontrado con ID: " + id)));
    }

    @GetMapping(value = "/usuario/{usuarioId}", produces = MediaType.APPLICATION_JSON)
    public Flux<RecursoDidacticoResponseDTO> getRecursosByUsuario(@PathVariable("usuarioId") UUID usuarioId) {
        return recursoService.getRecursosByUsuario(usuarioId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron recursos para el usuario con ID: " + usuarioId)));
    }

    @GetMapping(value = "/tipo/{tipo}", produces = MediaType.APPLICATION_JSON)
    public Flux<RecursoDidacticoResponseDTO> getRecursosByTipo(@PathVariable("tipo") String tipo) {
        return recursoService.getRecursosByTipo(tipo)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron recursos del tipo: " + tipo)));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<RecursoDidacticoResponseDTO> updateRecurso(@PathVariable("id") UUID id, @Valid @RequestBody RecursoDidacticoRequestDTO requestDTO) {
        return recursoService.updateRecurso(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso no encontrado para actualizar con ID: " + id)));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteRecurso(@PathVariable("id") UUID id) {
        return recursoService.deleteRecurso(id)
                .onErrorResume(e -> Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el recurso")));
    }
}