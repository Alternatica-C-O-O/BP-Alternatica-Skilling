package com.skilling.lms.shared.dtos.assessments.request;

import java.time.LocalDate;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.EvaluacionTipo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EvaluacionRequestDTO(
    @NotBlank(message = "El nombre de la evaluación no puede estar vacío")
    @Size(max = 255, message = "El nombre de la evaluación no puede exceder 255 caracteres")
    String nombreEvaluacion,

    @NotNull(message = "El tipo de evaluación no puede ser nulo")
    EvaluacionTipo tipoEvaluacion,

    @NotNull(message = "El porcentaje de calificación no puede ser nulo")
    @Min(value = 0, message = "El porcentaje debe ser positivo o cero")
    Double porcentajeCalificacion,

    @NotNull(message = "La fecha de vencimiento no puede ser nula")
    LocalDate fechaVencimiento,

    @NotBlank(message = "La descripción no puede estar vacía")
    String descripcion,

    @NotNull(message = "El peso relativo no puede ser nulo")
    @Min(value = 0, message = "El peso relativo debe ser positivo o cero")
    Double pesoRelativo,

    @NotNull(message = "El puntaje máximo no puede ser nulo")
    @Min(value = 0, message = "El puntaje máximo debe ser positivo o cero")
    Double puntajeMaximo,

    @NotNull(message = "El ID del curso ofertado no puede ser nulo")
    UUID cursoOfertadoId,

    @NotNull(message = "El ID del módulo no puede ser nulo")
    UUID moduloId
) {}
