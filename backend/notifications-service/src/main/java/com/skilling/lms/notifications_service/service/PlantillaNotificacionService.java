package com.skilling.lms.notifications_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.notifications.request.PlantillaNotificacionesRequestDTO;
import com.skilling.lms.shared.dtos.notifications.response.PlantillaNotificacionesResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlantillaNotificacionService {

    Mono<PlantillaNotificacionesResponseDTO> createPlantillaNotificacion(PlantillaNotificacionesRequestDTO requestDTO);
    Mono<PlantillaNotificacionesResponseDTO> getPlantillaNotificacionById(UUID id);
    Flux<PlantillaNotificacionesResponseDTO> getAllPlantillasNotificacion();
    Mono<PlantillaNotificacionesResponseDTO> updatePlantillaNotificacion(UUID id, PlantillaNotificacionesRequestDTO requestDTO);
    Mono<Void> deletePlantillaNotificacion(UUID id);
}
