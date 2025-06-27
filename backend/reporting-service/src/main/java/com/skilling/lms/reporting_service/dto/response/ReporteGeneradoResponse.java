package com.skilling.lms.reporting_service.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record ReporteGeneradoResponse(
        UUID id,
        String name,
        LocalDate fecCreation,
        String parameters,
        String url,
        UUID usersId
) {
}
