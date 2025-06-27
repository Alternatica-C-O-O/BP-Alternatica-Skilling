package com.skilling.lms.shared.dtos.notifications.request;

import java.util.UUID;

import com.skilling.lms.shared.models.enums.NotificacionTipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NotificacionesRequestDTO(
    @NotNull(message = "El tipo de notificación no puede ser vacío")
    NotificacionTipo tipoNotificacion,

    @NotBlank(message = "El título no puede estar vacío")
    @Size(max = 255, message = "El título no puede exceder 255 caracteres")
    String titulo,

    @NotBlank(message = "El contenido no puede estar vacío")
    String contenido,

    @Size(max = 255, message = "La URL de acción no puede exceder 255 caracteres")
    String urlAccion,

    @NotBlank(message = "El evento de origen no puede estar vacío")
    @Size(max = 255, message = "El evento de origen no puede exceder 255 caracteres")
    String eventoOrigen,

    @NotBlank(message = "La referencia ID no puede estar vacía")
    @Size(max = 100, message = "La referencia ID no puede exceder 100 caracteres")
    String referenciaId,

    @NotNull(message = "El ID de usuario no puede ser nulo")
    UUID usuariosId,

    @NotNull(message = "El ID de plantilla de notificación no puede ser nulo")
    UUID plantillaNotificacionesId
) {}
