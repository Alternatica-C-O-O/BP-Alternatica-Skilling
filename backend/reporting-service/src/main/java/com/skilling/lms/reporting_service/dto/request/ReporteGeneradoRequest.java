package com.skilling.lms.reporting_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReporteGeneradoRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        String name,
        @NotBlank(message = "Los parámetros no pueden estar vacíos")
        String parameters,
        @NotBlank(message = "La url no puede estar vacía")
        String url,
        @NotNull(message = "El ID de usuario es requerido")
        UUID usersId
) {
}
