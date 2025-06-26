package com.skilling.lms.assessments_service.dtos;

import java.util.UUID;
import java.time.LocalDate;

public record EvaluacionDto(
	UUID id, 
	String nombreEvaluacion, 
	Double porcentajeCalificacion,
	LocalDate fechaVencimiento,
	String descripcion,
	Double pesoRelativo,
	Double puntajeMaximo,
	UUID cursoOfertadoId,
	UUID moduloId
) {}
