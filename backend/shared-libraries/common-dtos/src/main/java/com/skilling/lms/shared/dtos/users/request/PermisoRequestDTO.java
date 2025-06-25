package com.skilling.lms.shared.dtos.users.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PermisoRequestDTO(
    @NotBlank(message = "El nombre del permiso no puede estar vacío")
    @Size(max = 50, message = "El nombre del permiso no puede exceder 50 caracteres")
    String nombrePermiso,

    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    String descripcion
) {}
