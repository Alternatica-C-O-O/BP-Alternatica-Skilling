package com.skilling.lms.shared.dtos.notifications.response;

import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.skilling.lms.shared.models.enums.CanalTipo;

public record PlantillaNotificacionesResponseDTO(
    UUID id,
    String nombreInterno,
    String asuntoPlantilla,
    String cuerpoPlantilla,
    JsonNode variablesDisponibles,
    CanalTipo canalPreferido
) {}
