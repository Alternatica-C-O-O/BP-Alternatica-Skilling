package com.skilling.lms.shared.dtos.reporting.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReporteGeneradoRequestDTO(
    @NotBlank(message = "El nombre del reporte no puede estar vacío")
    @Size(max = 255, message = "El nombre del reporte no puede exceder 255 caracteres")
    String nombreReporte,

    @NotBlank(message = "Los parámetros del reporte no pueden estar vacíos")
    String parametrosReporte,

    @NotBlank(message = "La URL del reporte no puede estar vacía")
    @Size(max = 255, message = "La URL del reporte no puede exceder 255 caracteres")
    String urlReporte,

    @NotNull(message = "El ID de usuario no puede ser nulo")
    UUID usuariosId
) {}