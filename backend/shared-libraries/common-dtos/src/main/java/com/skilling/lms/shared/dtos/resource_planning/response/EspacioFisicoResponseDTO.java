package com.skilling.lms.shared.dtos.resource_planning.response;

import java.util.UUID;

import com.skilling.lms.shared.models.enums.EspacioTipo;

public record EspacioFisicoResponseDTO(
                UUID id,
                String nombre,
                Integer capacidad,
                EspacioTipo tipoEspacio,
                String ubicacion) {
}
