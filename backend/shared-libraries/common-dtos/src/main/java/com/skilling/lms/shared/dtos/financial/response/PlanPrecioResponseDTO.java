package com.skilling.lms.shared.dtos.financial.response;

import java.time.LocalDate;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.PlanPrecioTipo;

public record PlanPrecioResponseDTO(
    UUID id,
    String nombrePlan,
    String version,
    LocalDate fechaAprobacion,
    PlanPrecioTipo estado,
    LocalDate fechaUltimaActualizacion,
    UUID cursoOfertado
) {}
