package com.skilling.lms.shared.dtos.reporting.response;

import java.time.LocalDate;
import java.util.UUID;

public record ReporteGeneradoResponseDTO(
    UUID id,
    String nombreReporte,
    LocalDate fechaGeneracion,
    String parametrosReporte,
    String urlReporte,
    UUID usuario
) {}