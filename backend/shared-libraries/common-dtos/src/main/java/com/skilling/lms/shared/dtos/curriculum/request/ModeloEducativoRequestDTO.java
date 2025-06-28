package com.skilling.lms.shared.dtos.curriculum.request;

import java.util.UUID;

import com.skilling.lms.shared.models.enums.GeneralEstado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ModeloEducativoRequestDTO(
    @NotBlank(message = "El nombre del modelo educativo no puede estar vacío")
    @Size(max = 255, message = "El nombre del modelo educativo no puede exceder 255 caracteres")
    String nombreModelo,

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    String descripcion,

    @NotBlank(message = "La versión no puede estar vacía")
    @Size(max = 100, message = "La versión no puede exceder 100 caracteres")
    String version,

    @NotNull(message = "El estado no puede ser nulo")
    GeneralEstado estado,

    @NotNull(message = "El ID de usuario no puede ser nulo")
    UUID usuariosId
) {}
