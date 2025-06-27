package com.skilling.lms.shared.dtos.enrollment.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record PublicacionForoResponseDTO(
    UUID id,
    String contenido,
    LocalDateTime fechaPublicacion,
    UUID usuario,
    UUID foro,
    UUID publicacionPadre
) {}