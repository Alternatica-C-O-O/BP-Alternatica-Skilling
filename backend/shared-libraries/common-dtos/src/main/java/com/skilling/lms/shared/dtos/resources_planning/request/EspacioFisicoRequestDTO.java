package com.skilling.lms.shared.dtos.resources_planning.request;

import com.skilling.lms.shared.models.enums.EspacioTipo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EspacioFisicoRequestDTO(
    @NotBlank(message = "El nombre del espacio físico no puede estar vacío")
    @Size(max = 255, message = "El nombre del espacio físico no puede exceder 255 caracteres")
    String nombre,

    @NotNull(message = "La capacidad no puede ser nula")
    @Min(value = 1, message = "La capacidad debe ser al menos 1")
    Integer capacidad,

    @NotNull(message = "El tipo de espacio no puede ser nulo")
    EspacioTipo tipoEspacio,

    @NotBlank(message = "La ubicación no puede estar vacía")
    @Size(max = 255, message = "La ubicación no puede exceder 255 caracteres")
    String ubicacion
) {}