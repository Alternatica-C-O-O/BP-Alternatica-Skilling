package com.skilling.lms.shared.dtos.assessments.response;

import java.time.LocalDate;
import java.util.UUID;

public record CalificacionResponseDTO(
    UUID id,
    Double puntajeObtenido,
    LocalDate fechaCalificacion,
    String comentariosDocente,
    UUID inscripcion, 
    UUID evaluacion
) {}