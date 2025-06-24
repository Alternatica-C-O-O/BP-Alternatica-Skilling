package com.skilling.lms.resource_planning_service.dtos;

import java.util.UUID;

public record AsignacionHorarioDto(
	UUID id, String diaSemana, String horaInicio, String horaFin, String tipoAsignacion, UUID espacioFisicoId,
	UUID plataformaVirtualId
) {

}
