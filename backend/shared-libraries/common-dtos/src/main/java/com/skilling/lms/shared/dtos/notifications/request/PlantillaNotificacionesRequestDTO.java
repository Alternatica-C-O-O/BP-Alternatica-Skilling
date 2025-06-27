package com.skilling.lms.shared.dtos.notifications.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.skilling.lms.shared.models.enums.CanalTipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PlantillaNotificacionesRequestDTO(
    @NotBlank(message = "El nombre interno no puede estar vacío")
    @Size(max = 255, message = "El nombre interno no puede exceder 255 caracteres")
    String nombreInterno,
    
    @NotBlank(message = "El asunto de la plantilla no puede estar vacío")
    @Size(max = 255, message = "El asunto de la plantilla no puede exceder 255 caracteres")
    String asuntoPlantilla,

    @NotBlank(message = "El cuerpo de la plantilla no puede estar vacío")
    String cuerpoPlantilla,

    @NotNull(message = "Las variables disponibles no pueden ser nulas")
    JsonNode variablesDisponibles,
    
    @NotNull(message = "El canal preferido no puede ser nulo")
    CanalTipo canalPreferido
) {}
