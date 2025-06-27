package com.skilling.lms.shared.dtos.enrollment.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MensajeRequestDTO(
    @Size(max = 255, message = "El asunto no puede exceder 255 caracteres")
    String asunto, 

    @NotBlank(message = "El contenido no puede estar vacío")
    String contenido,

    @NotNull(message = "El ID de usuario no puede ser nulo")
    UUID usuariosId,

    @NotNull(message = "El ID de foro no puede ser nulo")
    UUID foroId
) {}
