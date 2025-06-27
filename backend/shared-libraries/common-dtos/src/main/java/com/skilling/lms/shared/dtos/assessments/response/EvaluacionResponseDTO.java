package com.skilling.lms.shared.dtos.assessments.response;

import java.time.LocalDate;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.EvaluacionTipo;

public record EvaluacionResponseDTO(
    UUID id,
    String nombreEvaluacion,
    EvaluacionTipo tipoEvaluacion,
    Double porcentajeCalificacion,
    LocalDate fechaVencimiento,
    String descripcion,
    Double pesoRelativo,
    Double puntajeMaximo,
    UUID cursoOfertado,
    UUID modulo 
) {}
