package com.skilling.lms.shared.dtos.curriculum.request;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ModuloRequestDTO(
    @NotBlank(message = "El nombre del módulo no puede estar vacío")
    @Size(max = 255, message = "El nombre del módulo no puede exceder 255 caracteres")
    String nombreModulo,

    @NotNull(message = "El orden no puede ser nulo")
    @Min(value = 1, message = "El orden debe ser al menos 1")
    Integer orden,

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    String descripcion,

    @NotBlank(message = "Los objetivos de aprendizaje no pueden estar vacíos")
    String objetivosAprendizaje,

    @NotNull(message = "El ID del curso ofertado no puede ser nulo")
    UUID cursoOfertadoId
) {}