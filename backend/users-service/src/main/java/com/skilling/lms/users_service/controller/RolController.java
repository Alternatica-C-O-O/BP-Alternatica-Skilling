package com.skilling.lms.users_service.controller;

import java.util.Set;
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

import com.skilling.lms.shared.dtos.users.request.RolRequestDTO;
import com.skilling.lms.shared.dtos.users.response.RolResponseDTO;
import com.skilling.lms.users_service.service.RolService;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<RolResponseDTO> createRol(@Valid @RequestBody RolRequestDTO requestDTO) {
        return rolService.createRol(requestDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<RolResponseDTO> getAllRoles() {
        return rolService.getAllRoles();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<RolResponseDTO> getRolById(@PathVariable UUID id) {
        return rolService.getRolById(id)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<RolResponseDTO> updateRol(@PathVariable UUID id, @Valid @RequestBody RolRequestDTO requestDTO) {
        return rolService.updateRol(id, requestDTO)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteRol(@PathVariable UUID id) {
        return rolService.deleteRol(id);
    }

    // --- Endpoints para manejar la relación N:M con Permisos ---

    @PostMapping(value = "/{rolId}/permisos", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<RolResponseDTO> assignPermisosToRol(@PathVariable UUID rolId, @RequestBody Set<UUID> permisoIds) {
        return rolService.assignPermisosToRol(rolId, permisoIds)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado para asignar permisos")));
    }

    @DeleteMapping(value = "/{rolId}/permisos", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<RolResponseDTO> removePermisosFromRol(@PathVariable UUID rolId, @RequestBody Set<UUID> permisoIds) {
        return rolService.removePermisosFromRol(rolId, permisoIds)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado para remover permisos")));
    }
}
