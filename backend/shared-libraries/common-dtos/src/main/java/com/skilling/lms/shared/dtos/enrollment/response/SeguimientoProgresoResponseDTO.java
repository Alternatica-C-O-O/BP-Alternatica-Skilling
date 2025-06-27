package com.skilling.lms.shared.dtos.enrollment.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record SeguimientoProgresoResponseDTO(
    UUID id,
    LocalDateTime fechaUltimoAcceso,
    Boolean completado,
    Double puntajeObtenidoModulo,
    UUID inscripcion,
    UUID modulo 
) {}