package com.skilling.lms.shared.dtos.enrollment.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ForoRequestDTO(
    @NotBlank(message = "El nombre del foro no puede estar vacío")
    @Size(max = 255, message = "El nombre del foro no puede exceder 255 caracteres")
    String nombreForo,

    @NotBlank(message = "La descripción no puede estar vacía")
    String descripcion,

    @NotNull(message = "El ID del curso ofertado no puede ser nulo")
    UUID cursoOfertadoId
) {}