package com.skilling.lms.shared.dtos.reporting.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.EventoTipo;

public record LogActividadResponseDTO(
    UUID id,
    LocalDateTime fechaHora,
    EventoTipo tipoEvento,
    String detalleEvento,
    String ipOrigen,
    UUID usuario
) {}