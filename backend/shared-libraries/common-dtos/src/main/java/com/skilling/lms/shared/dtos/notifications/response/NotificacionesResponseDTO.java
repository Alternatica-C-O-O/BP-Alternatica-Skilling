package com.skilling.lms.shared.dtos.notifications.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.NotificacionTipo;

public record NotificacionesResponseDTO(
    UUID id,
    NotificacionTipo tipoNotificacion,
    String titulo,
    String contenido,
    LocalDateTime fechaEnvio,
    LocalDateTime fechaLectura,
    Boolean estaLeida,
    String urlAccion,
    String eventoOrigen,
    String referenciaId,
    UUID usuario,
    UUID plantillaNotificacion
) {}
