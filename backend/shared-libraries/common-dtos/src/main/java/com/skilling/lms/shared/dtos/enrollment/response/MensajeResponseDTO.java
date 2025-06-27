package com.skilling.lms.shared.dtos.enrollment.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record MensajeResponseDTO(
    UUID id,
    String asunto,
    String contenido,
    LocalDateTime fechaEnvio,
    Boolean leido,
    UUID usuario,
    UUID foro
) {}