package com.skilling.lms.shared.dtos.resources_planning.request;

import com.skilling.lms.shared.models.enums.PlataformaVirtualTipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PlataformaVirtualRequestDTO(
    @NotBlank(message = "El nombre de la plataforma no puede estar vacío")
    @Size(max = 255, message = "El nombre de la plataforma no puede exceder 255 caracteres")
    String nombrePlataforma,

    @NotBlank(message = "La URL no puede estar vacía")
    @Size(max = 255, message = "La URL no puede exceder 255 caracteres")
    String url,

    @NotNull(message = "El tipo no puede ser nulo")
    PlataformaVirtualTipo tipo,

    @NotBlank(message = "Las credenciales API no pueden estar vacías")
    String credencialesApi
) {}