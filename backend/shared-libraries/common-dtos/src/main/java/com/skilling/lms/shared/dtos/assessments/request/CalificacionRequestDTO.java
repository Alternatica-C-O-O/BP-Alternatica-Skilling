package com.skilling.lms.shared.dtos.assessments.request;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CalificacionRequestDTO(
    @NotNull(message = "El puntaje obtenido no puede ser nulo")
    @Min(value = 0, message = "El puntaje obtenido debe ser positivo o cero")
    Double puntajeObtenido,

    @NotBlank(message = "Los comentarios del docente no pueden estar vacíos")
    String comentariosDocente,

    @NotNull(message = "El ID de inscripción no puede ser nulo")
    UUID inscripcionId,

    @NotNull(message = "El ID de evaluación no puede ser nulo")
    UUID evaluacionId
) {}
