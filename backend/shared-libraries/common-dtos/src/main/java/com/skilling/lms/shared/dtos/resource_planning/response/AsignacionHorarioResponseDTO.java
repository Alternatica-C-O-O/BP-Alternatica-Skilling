package com.skilling.lms.shared.dtos.resource_planning.response;

import java.time.LocalTime;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.AsignacionTipo;

public record AsignacionHorarioResponseDTO(
		UUID id,

		UUID espacioFisicoId, String nombreEspacioFisico,

		UUID plataformaVirtualId, String nombrePlataforma,

		UUID periodoAcademicoId, String nombrePeriodoAcademico,

		AsignacionTipo tipoAsignacion,

		LocalTime horaInicio, LocalTime horaFin, Integer diaSemana) {
}
