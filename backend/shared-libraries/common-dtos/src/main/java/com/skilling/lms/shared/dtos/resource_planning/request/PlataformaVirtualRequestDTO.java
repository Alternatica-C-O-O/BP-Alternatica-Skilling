package com.skilling.lms.shared.dtos.resource_planning.request;

import com.skilling.lms.shared.models.enums.PlataformaVirtualTipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PlataformaVirtualRequestDTO(

    @NotBlank(message = "El nombre de la plataforma no puede estar vacío") @Size(max = 100, message = "El nombre no puede exceder 100 caracteres") String nombrePlataforma,

    @NotBlank(message = "La URL es obligatoria") @Size(max = 255, message = "La URL no puede exceder 255 caracteres") String url,

    @NotNull(message = "El tipo de plataforma es obligatorio") PlataformaVirtualTipo tipo,

    @Size(max = 500, message = "Las credenciales no pueden exceder 500 caracteres") String credencialesApi) {
}
