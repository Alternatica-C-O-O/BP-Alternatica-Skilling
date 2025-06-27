package com.skilling.lms.shared.dtos.curriculum.request;

import java.time.LocalDate;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.GeneralEstado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PlanEstudioRequestDTO(
    @NotBlank(message = "El nombre del plan de estudio no puede estar vacío")
    @Size(max = 255, message = "El nombre del plan de estudio no puede exceder 255 caracteres")
    String nombrePlan,

    @NotBlank(message = "La versión no puede estar vacía")
    @Size(max = 150, message = "La versión no puede exceder 150 caracteres")
    String version,

    @NotNull(message = "La fecha de aprobación no puede ser nula")
    LocalDate fechaAprobacion,

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 150, message = "El estado no puede exceder 150 caracteres")
    GeneralEstado estado,

    @NotNull(message = "El ID del modelo educativo no puede ser nulo")
    UUID modeloEducativoId
) {}
