package com.skilling.lms.shared.dtos.resource_planning.response;

import java.time.LocalDate;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.PeriodoAcademicoEstado;
import com.skilling.lms.shared.models.enums.PeriodoAcademicoTipo;

public record PeriodoAcademicoResponseDTO(
                UUID id,
                String nombre,
                LocalDate fechaInicio,
                LocalDate fechaFin,
                PeriodoAcademicoEstado estado,
                PeriodoAcademicoTipo tipoPeriodo) {
}
