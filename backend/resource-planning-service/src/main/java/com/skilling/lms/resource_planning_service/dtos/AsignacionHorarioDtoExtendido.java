package com.skilling.lms.resource_planning_service.dtos;

import java.util.UUID;

public record AsignacionHorarioDtoExtendido(
	UUID id, 
	String diaSemana, 
	String horaInicio, 
	String horaFin, 
	String tipoAsignacion,
	EspacioFisicoDto espacioFisico, 
	PlataformaVirtualDto plataformaVirtual
) {
}
