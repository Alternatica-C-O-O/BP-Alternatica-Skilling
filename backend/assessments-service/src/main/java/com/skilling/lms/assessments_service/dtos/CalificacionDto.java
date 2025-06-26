package com.skilling.lms.assessments_service.dtos;

import java.util.UUID;
import java.time.LocalDate;

public record CalificacionDto(
	UUID id,
	Double puntajeObtenido, 
	LocalDate fechaCalificacion, 
	String comentariosDocente, 
	UUID inscripcionId, 
	UUID evaluacionId
) {
}
