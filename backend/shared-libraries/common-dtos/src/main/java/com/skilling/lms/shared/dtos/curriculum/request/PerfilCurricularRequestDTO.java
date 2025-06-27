package com.skilling.lms.shared.dtos.curriculum.request;

import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PerfilCurricularRequestDTO(
    @NotBlank(message = "El nombre del perfil curricular no puede estar vacío")
    @Size(max = 255, message = "El nombre del perfil curricular no puede exceder 255 caracteres")
    String nombrePerfil,

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    String descripcion,

    @NotNull(message = "El ID del modelo educativo no puede ser nulo")
    UUID modeloEducativoId,

    Set<UUID> competenciaIds
) {}