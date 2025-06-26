package com.skilling.lms.shared.dtos.resource_planning.request;

import com.skilling.lms.shared.models.enums.EspacioTipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EspacioFisicoRequestDTO(

    @NotBlank(message = "El nombre del espacio físico no puede estar vacío") @Size(max = 100, message = "El nombre no puede exceder 100 caracteres") String nombre,

    @NotNull(message = "La capacidad es obligatoria") @Positive(message = "La capacidad debe ser un número positivo") Integer capacidad,

    @NotNull(message = "El tipo de espacio es obligatorio") EspacioTipo tipoEspacio,

    @Size(max = 255, message = "La ubicación no puede exceder 255 caracteres") String ubicacion) {
}
