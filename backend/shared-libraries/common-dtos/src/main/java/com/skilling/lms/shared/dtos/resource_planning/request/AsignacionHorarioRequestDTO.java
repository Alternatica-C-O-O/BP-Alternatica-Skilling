package com.skilling.lms.shared.dtos.resource_planning.request;

import java.time.LocalTime;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.AsignacionTipo;

import jakarta.validation.constraints.NotNull;

public record AsignacionHorarioRequestDTO(

	@NotNull(message = "El ID del espacio físico es obligatorio") UUID espacioFisicoId,

	@NotNull(message = "El ID de la plataforma virtual es obligatorio") UUID plataformaVirtualId,

	@NotNull(message = "El ID del periodo académico es obligatorio") UUID periodoAcademicoId,

	@NotNull(message = "El ID del usuario es obligatorio") UUID usuariosId,

	@NotNull(message = "El ID del curso ofertado es obligatorio") UUID cursoOfertadoId,

	@NotNull(message = "El tipo de asignación es obligatorio") AsignacionTipo tipoAsignacion,

	@NotNull(message = "La hora de inicio es obligatoria") LocalTime horaInicio,

	@NotNull(message = "La hora de fin es obligatoria") LocalTime horaFin,

	@NotNull(message = "El día de la semana es obligatorio") Integer diaSemana
) {
}
