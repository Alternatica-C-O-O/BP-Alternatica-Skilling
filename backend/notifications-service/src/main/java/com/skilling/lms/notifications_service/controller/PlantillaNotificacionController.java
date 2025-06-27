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

import com.skilling.lms.notifications_service.service.PlantillaNotificacionService;
import com.skilling.lms.shared.dtos.notifications.request.PlantillaNotificacionesRequestDTO;
import com.skilling.lms.shared.dtos.notifications.response.PlantillaNotificacionesResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plantillas-notificaciones")
public class PlantillaNotificacionController {

    private final PlantillaNotificacionService plantillaNotificacionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PlantillaNotificacionesResponseDTO> createPlantillaNotificacion(@Valid @RequestBody PlantillaNotificacionesRequestDTO requestDTO) {
        return plantillaNotificacionService.createPlantillaNotificacion(requestDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<PlantillaNotificacionesResponseDTO> getAllPlantillasNotificacion() {
        return plantillaNotificacionService.getAllPlantillasNotificacion();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<PlantillaNotificacionesResponseDTO> getPlantillaNotificacionById(@PathVariable UUID id) {
        return plantillaNotificacionService.getPlantillaNotificacionById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Plantilla Notificación no encontrada")));
    }
    
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<PlantillaNotificacionesResponseDTO> updatePlantillaNotificacion(@PathVariable UUID id, @Valid @RequestBody PlantillaNotificacionesRequestDTO requestDTO) {
        return plantillaNotificacionService.updatePlantillaNotificacion(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Plantilla notificación no encontrada para actualizar")));
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePlantillaNotificacion(@PathVariable UUID id) {
        return plantillaNotificacionService.deletePlantillaNotificacion(id);
    }
}
