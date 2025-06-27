package com.skilling.lms.shared.dtos.enrollment.request;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SeguimientoProgresoRequestDTO(
    @NotNull(message = "El estado completado no puede ser nulo")
    Boolean completado,

    @NotNull(message = "El puntaje obtenido del módulo no puede ser nulo")
    @Min(value = 0, message = "El puntaje debe ser un número positivo o cero")
    Double puntajeObtenidoModulo,

    @NotNull(message = "El ID de inscripción no puede ser nulo")
    UUID inscripcionId,

    @NotNull(message = "El ID de módulo no puede ser nulo")
    UUID moduloId
) {}
