package com.skilling.lms.shared.dtos.resources_planning.request;

import java.time.LocalDate;

import com.skilling.lms.shared.models.enums.PeriodoAcademicoEstado;
import com.skilling.lms.shared.models.enums.PeriodoAcademicoTipo;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PeriodoAcademicoRequestDTO(
    @NotBlank(message = "El nombre del periodo académico no puede estar vacío")
    @Size(max = 255, message = "El nombre del periodo académico no puede exceder 255 caracteres")
    String nombre,

    @NotNull(message = "La fecha de inicio no puede ser nula")
    LocalDate fechaInicio,

    @NotNull(message = "La fecha de fin no puede ser nula")
    @FutureOrPresent(message = "La fecha de fin debe ser en el presente o futuro")
    LocalDate fechaFin,

    @NotNull(message = "El estado no puede ser nulo")
    PeriodoAcademicoEstado estado,

    @NotNull(message = "El tipo de periodo no puede ser nulo")
    PeriodoAcademicoTipo tipoPeriodo
) {}
