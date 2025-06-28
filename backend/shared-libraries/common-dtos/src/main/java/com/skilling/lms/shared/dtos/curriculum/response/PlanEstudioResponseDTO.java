package com.skilling.lms.shared.dtos.curriculum.response;

import java.time.LocalDate;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.GeneralEstado;

public record PlanEstudioResponseDTO(
    UUID id,
    String nombrePlan,
    String version,
    LocalDate fechaAprobacion,
    GeneralEstado estado,
    LocalDate fechaUltimaActualizacion,
    UUID modeloEducativo
) {}
