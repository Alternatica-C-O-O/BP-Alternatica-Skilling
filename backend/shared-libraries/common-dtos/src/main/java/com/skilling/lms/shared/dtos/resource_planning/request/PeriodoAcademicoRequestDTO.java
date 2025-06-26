package com.skilling.lms.shared.dtos.resource_planning.request;

import java.time.LocalDate;

import com.skilling.lms.shared.models.enums.PeriodoAcademicoEstado;
import com.skilling.lms.shared.models.enums.PeriodoAcademicoTipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PeriodoAcademicoRequestDTO(

    @NotBlank(message = "El nombre del período académico no puede estar vacío") @Size(max = 100, message = "El nombre no puede exceder 100 caracteres") String nombre,

    @NotNull(message = "La fecha de inicio es obligatoria") LocalDate fechaInicio,

    @NotNull(message = "La fecha de fin es obligatoria") LocalDate fechaFin,

    @NotNull(message = "El estado es obligatorio") PeriodoAcademicoEstado estado,

    @NotNull(message = "El tipo de período es obligatorio") PeriodoAcademicoTipo tipoPeriodo) {
}
