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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.skilling.lms.shared.dtos.users.request.UsuarioRequestDTO;
import com.skilling.lms.shared.dtos.users.response.UsuarioResponseDTO;
import com.skilling.lms.users_service.service.UsuarioService;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<UsuarioResponseDTO> createUsuario(@Valid @RequestBody UsuarioRequestDTO requestDTO) {
        return usuarioService.createUsuario(requestDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<UsuarioResponseDTO> getUsuarioById(@PathVariable UUID id) {
        return usuarioService.getUsuarioById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado")));
    }

    @GetMapping(value = "/by-email", produces = MediaType.APPLICATION_JSON)
    public Mono<UsuarioResponseDTO> getUsuarioByEmail(@RequestParam String email) {
        return usuarioService.getUsuarioByEmail(email)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado por email")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<UsuarioResponseDTO> updateUsuario(@PathVariable UUID id, @Valid @RequestBody UsuarioRequestDTO requestDTO) {
        return usuarioService.updateUsuario(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado para actualizar")));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteUsuario(@PathVariable UUID id) {
        return usuarioService.deleteUsuario(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado para eliminar")));
    }

    // --- Endpoints para manejar la relación N:M con Roles ---

    @PostMapping(value = "/{usuarioId}/roles", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<UsuarioResponseDTO> assignRolesToUsuario(@PathVariable UUID usuarioId, @RequestBody Set<UUID> rolIds) {
        return usuarioService.assignRolesToUsuario(usuarioId, rolIds)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado para asignar roles")));
    }

    @DeleteMapping(value = "/{usuarioId}/roles", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<UsuarioResponseDTO> removeRolesFromUsuario(@PathVariable UUID usuarioId, @RequestBody Set<UUID> rolIds) {
        return usuarioService.removeRolesFromUsuario(usuarioId, rolIds)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado para remover roles")));
    }
}
