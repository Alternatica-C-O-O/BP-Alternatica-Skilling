package com.skilling.lms.shared.dtos.enrollment.request;

import java.util.UUID;

import com.skilling.lms.shared.models.enums.AsistenciaEstado;

import jakarta.validation.constraints.NotNull;

public record AsistenciaRequestDTO(
    @NotNull(message = "El estado de asistencia no puede ser nulo")
    AsistenciaEstado estadoAsistencia,

    @NotNull(message = "El ID de inscripción no puede ser nulo")
    UUID inscripcionId
) {}