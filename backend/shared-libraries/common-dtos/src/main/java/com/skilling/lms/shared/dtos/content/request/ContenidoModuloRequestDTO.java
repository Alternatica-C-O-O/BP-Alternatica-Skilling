package com.skilling.lms.shared.dtos.content.request;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ContenidoModuloRequestDTO(
    @NotNull(message = "El orden del módulo no puede ser nulo")
    @Min(value = 0, message = "El orden debe ser un número positivo o cero")
    Integer ordenModulo,

    @NotNull(message = "El ID del recurso didáctico no puede ser nulo")
    UUID recursoDidacticoId,

    @NotNull(message = "El ID del módulo no puede ser nulo")
    UUID moduloId
) {}
