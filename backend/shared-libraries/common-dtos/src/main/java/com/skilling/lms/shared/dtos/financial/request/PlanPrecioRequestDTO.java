package com.skilling.lms.shared.dtos.financial.request;

import java.time.LocalDate;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.PlanPrecioTipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PlanPrecioRequestDTO(
    @NotBlank(message = "El nombre del plan no puede estar vacío")
    @Size(max = 255, message = "El nombre del plan no puede exceder 255 caracteres")
    String nombrePlan,

    @NotBlank(message = "La versión no puede estar vacía")
    @Size(max = 150, message = "La versión no puede exceder 150 caracteres")
    String version,

    @NotNull(message = "La fecha de aprobación no puede ser nula")
    LocalDate fechaAprobacion,

    @NotNull(message = "El estado no puede ser nulo")
    PlanPrecioTipo estado,

    @NotNull(message = "El ID del curso ofertado no puede ser nulo")
    UUID cursoOfertadoId
) {}