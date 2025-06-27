package com.skilling.lms.shared.dtos.enrollment.response;

import java.time.LocalDate;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.InscripcionEstado;

public record InscripcionResponseDTO(
    UUID id,
    LocalDate fechaInscripcion,
    InscripcionEstado estado,
    LocalDate fechaFinalizacion,
    Double progresoPorcentaje,
    UUID usuario,
    UUID cursoOfertado
) {}
