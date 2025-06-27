package com.skilling.lms.shared.dtos.enrollment.request;

import java.time.LocalDate;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.InscripcionEstado;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InscripcionRequestDTO(
    @NotNull(message = "El estado no puede ser nulo")
    InscripcionEstado estado,

    LocalDate fechaFinalizacion,

    @NotNull(message = "El porcentaje de progreso no puede ser nulo")
    @Min(value = 0, message = "El progreso debe ser al menos 0%")
    @Max(value = 100, message = "El progreso no puede exceder 100%")
    Double progresoPorcentaje,

    @NotNull(message = "El ID de usuario no puede ser nulo")
    UUID usuariosId,

    @NotNull(message = "El ID del curso ofertado no puede ser nulo")
    UUID cursoOfertadoId
) {}