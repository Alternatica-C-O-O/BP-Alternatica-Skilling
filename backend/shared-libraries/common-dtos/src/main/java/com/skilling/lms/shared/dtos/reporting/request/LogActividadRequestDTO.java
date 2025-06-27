package com.skilling.lms.shared.dtos.reporting.request;

import java.util.UUID;

import com.skilling.lms.shared.models.enums.EventoTipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LogActividadRequestDTO(
    @NotNull(message = "El tipo de evento no puede ser nulo")
    EventoTipo tipoEvento,

    @NotBlank(message = "El detalle del evento no puede estar vacío")
    String detalleEvento,

    @NotBlank(message = "La IP de origen no puede estar vacía")
    @Size(max = 100, message = "La IP de origen no puede exceder 100 caracteres")
    String ipOrigen,

    @NotNull(message = "El ID de usuario no puede ser nulo")
    UUID usuariosId
) {}