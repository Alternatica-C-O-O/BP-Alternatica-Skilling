package com.skilling.lms.notifications_service.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skilling.lms.notifications_service.domains.PlantillaNotificacion;
import com.skilling.lms.notifications_service.repositories.PlantillaNotificacionRepository;
import com.skilling.lms.notifications_service.service.PlantillaNotificacionService;
import com.skilling.lms.shared.dtos.notifications.request.PlantillaNotificacionesRequestDTO;
import com.skilling.lms.shared.dtos.notifications.response.PlantillaNotificacionesResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.r2dbc.postgresql.codec.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlantillaNotificacionServiceImpl implements PlantillaNotificacionService {
    
    private final ObjectMapper objectMapper; 
    private final PlantillaNotificacionRepository plantillaNotificacionRepository;

    private static final String PLANTILLA_NOTIFICACIONES_SERVICE_CB = "plantillaNotificacionesServiceCB";

    @Override
    @CircuitBreaker(name = PLANTILLA_NOTIFICACIONES_SERVICE_CB, fallbackMethod = "fallbackCreatePlantillaNotificacion")
    public Mono<PlantillaNotificacionesResponseDTO> createPlantillaNotificacion(
            PlantillaNotificacionesRequestDTO requestDTO) {

        Json jsonMapping = null;
        if (requestDTO.variablesDisponibles() != null) {
            try {
                jsonMapping = Json.of(objectMapper.writeValueAsString(requestDTO.variablesDisponibles()));
            } catch (Exception e) {
                log.error("Error al convertir JsonNode de Request a Json de DB: {}", e.getMessage());
                return Mono.error(new RuntimeException("Error al procesar variables Disponibles.", e));
            }
        }
        
        PlantillaNotificacion plantilla = PlantillaNotificacion.builder()
                .nombreInterno(requestDTO.nombreInterno())
                .asuntoPlantilla(requestDTO.asuntoPlantilla())
                .cuerpoPlantilla(requestDTO.cuerpoPlantilla())
                .variablesDisponibles(jsonMapping)
                .canalPreferido(requestDTO.canalPreferido())
                .build();

        return plantillaNotificacionRepository.save(plantilla)
                .map(this::toPlantillaNotificacionesResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PlantillaNotificacionesResponseDTO> fallbackCreatePlantillaNotificacion(
            PlantillaNotificacionesRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createPlantillaNotificacion activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Plantillas de Notificación no está disponible o falló al crear la plantilla."));
    }

    @Override
    @CircuitBreaker(name = PLANTILLA_NOTIFICACIONES_SERVICE_CB, fallbackMethod = "fallbackGetPlantillaNotificacionById")
    public Mono<PlantillaNotificacionesResponseDTO> getPlantillaNotificacionById(UUID id) {
        return plantillaNotificacionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Plantilla de Notificación no encontrada con ID: " + id)))
                .map(this::toPlantillaNotificacionesResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PlantillaNotificacionesResponseDTO> fallbackGetPlantillaNotificacionById(UUID id, Throwable t) {
        log.error("Fallback para getPlantillaNotificacionById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Plantillas de Notificación no está disponible o falló al obtener la plantilla."));
    }

    @Override
    @CircuitBreaker(name = PLANTILLA_NOTIFICACIONES_SERVICE_CB, fallbackMethod = "fallbackGetAllPlantillasNotificacion")
    public Flux<PlantillaNotificacionesResponseDTO> getAllPlantillasNotificacion() {
        return plantillaNotificacionRepository.findAll()
                .map(this::toPlantillaNotificacionesResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<PlantillaNotificacionesResponseDTO> fallbackGetAllPlantillasNotificacion(Throwable t) {
        log.error("Fallback para getAllPlantillasNotificacion activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = PLANTILLA_NOTIFICACIONES_SERVICE_CB, fallbackMethod = "fallbackUpdatePlantillaNotificacion")
    public Mono<PlantillaNotificacionesResponseDTO> updatePlantillaNotificacion(UUID id,
            PlantillaNotificacionesRequestDTO requestDTO) {

        return plantillaNotificacionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Plantilla de Notificación no encontrada para actualizar con ID: " + id)))
                .flatMap(existingPlantilla -> {
                    Json jsonMapping = null;
                    if (requestDTO.variablesDisponibles() != null) {
                        try {
                            jsonMapping = Json.of(objectMapper.writeValueAsString(requestDTO.variablesDisponibles()));
                        } catch (Exception e) {
                            log.error("Error al convertir JsonNode de Request a Json de DB: {}", e.getMessage());
                            return Mono.error(new RuntimeException("Error al procesar variables Disponibles.", e));
                        }
                    }
                    existingPlantilla.setNombreInterno(requestDTO.nombreInterno());
                    existingPlantilla.setAsuntoPlantilla(requestDTO.asuntoPlantilla());
                    existingPlantilla.setCuerpoPlantilla(requestDTO.cuerpoPlantilla());
                    existingPlantilla.setVariablesDisponibles(jsonMapping);
                    existingPlantilla.setCanalPreferido(requestDTO.canalPreferido());

                    return plantillaNotificacionRepository.save(existingPlantilla);
                })
                .map(this::toPlantillaNotificacionesResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PlantillaNotificacionesResponseDTO> fallbackUpdatePlantillaNotificacion(
            UUID id, PlantillaNotificacionesRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updatePlantillaNotificacion activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Plantillas de Notificación no está disponible o falló al actualizar la plantilla."));
    }

    @Override
    @CircuitBreaker(name = PLANTILLA_NOTIFICACIONES_SERVICE_CB, fallbackMethod = "fallbackDeletePlantillaNotificacion")
    public Mono<Void> deletePlantillaNotificacion(UUID id) {
        return plantillaNotificacionRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeletePlantillaNotificacion(UUID id, Throwable t) {
        log.error("Fallback para deletePlantillaNotificacion activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Plantillas de Notificación no está disponible o falló al eliminar la plantilla."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private PlantillaNotificacionesResponseDTO toPlantillaNotificacionesResponseDTO(
            PlantillaNotificacion plantilla) {
        
        JsonNode jsonNodeForResponse = null;
        if (plantilla.getVariablesDisponibles() != null) {
            try {
                jsonNodeForResponse = objectMapper.readTree(plantilla.getVariablesDisponibles().asString());
            } catch (Exception e) {
                log.error("Error al convertir Json de DB ({}) a JsonNode para la respuesta: {}",
                          plantilla.getVariablesDisponibles().asString(), e.getMessage());
                jsonNodeForResponse = objectMapper.createObjectNode(); 
            }
        }
        return new PlantillaNotificacionesResponseDTO(
            plantilla.getId(),
            plantilla.getNombreInterno(),
            plantilla.getAsuntoPlantilla(),
            plantilla.getCuerpoPlantilla(),
            jsonNodeForResponse,
            plantilla.getCanalPreferido()
        );
    }
}