package com.skilling.lms.notifications_service.controller;

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

import com.skilling.lms.notifications_service.service.NotificacionService;
import com.skilling.lms.shared.dtos.notifications.request.NotificacionesRequestDTO;
import com.skilling.lms.shared.dtos.notifications.response.NotificacionesResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<NotificacionesResponseDTO> createNotificacion(@Valid @RequestBody NotificacionesRequestDTO requestDTO) {
        return notificacionService.createNotificacion(requestDTO);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<NotificacionesResponseDTO> getAllNotificaciones() {
        return notificacionService.getAllNotificaciones();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<NotificacionesResponseDTO> getNotificacionById(@PathVariable UUID id) {
        return notificacionService.getNotificacionById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Notificacion no encontrada")));
    }
    
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<NotificacionesResponseDTO> updateNotificacion(@PathVariable UUID id, @Valid @RequestBody NotificacionesRequestDTO requestDTO) {
        return notificacionService.updateNotificacion(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Notificacion no encontrada para actualizar")));
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteNotificacion(@PathVariable UUID id) {
        return notificacionService.deleteNotificacion(id);
    }
}
