package com.skilling.lms.shared.dtos.curriculum.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompetenciaRequestDTO(
    @NotBlank(message = "El nombre de la competencia no puede estar vacío")
    @Size(max = 255, message = "El nombre de la competencia no puede exceder 255 caracteres")
    String nombreCompetencia,

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    String descripcion,

    @NotBlank(message = "El nivel esperado no puede estar vacío")
    @Size(max = 255, message = "El nivel esperado no puede exceder 255 caracteres")
    String nivelEsperado
) {}