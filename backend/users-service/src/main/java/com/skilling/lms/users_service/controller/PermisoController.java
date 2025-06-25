package com.skilling.lms.users_service.controller;

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

import com.skilling.lms.shared.dtos.users.request.PermisoRequestDTO;
import com.skilling.lms.shared.dtos.users.response.PermisoResponseDTO;
import com.skilling.lms.users_service.service.PermisoService;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/permisos")
public class PermisoController {

    private final PermisoService permisoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PermisoResponseDTO> createPermiso(@Valid @RequestBody PermisoRequestDTO requestDTO) {
        return permisoService.createPermiso(requestDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<PermisoResponseDTO> getAllPermisos() {
        return permisoService.getAllPermisos();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<PermisoResponseDTO> getPermisoById(@PathVariable UUID id) {
        return permisoService.getPermisoById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Permiso no encontrado")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PermisoResponseDTO> updatePermiso(@PathVariable UUID id, @Valid @RequestBody PermisoRequestDTO requestDTO) {
        return permisoService.updatePermiso(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Permiso no encontrado para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePermiso(@PathVariable UUID id) {
        return permisoService.deletePermiso(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "Permiso no encontrado para eliminar")));
    }
}
