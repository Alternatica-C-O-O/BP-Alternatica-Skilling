package com.skilling.lms.shared.dtos.users.request;

import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RolRequestDTO(
    @NotBlank(message = "El nombre del rol no puede estar vacío")
    @Size(max = 50, message = "El nombre del rol no puede exceder 50 caracteres")
    String nombreRol,

    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    String descripcion,

    Set<UUID> permisoIds 
) {}
