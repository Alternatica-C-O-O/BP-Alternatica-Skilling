package com.skilling.lms.shared.dtos.enrollment.response;

import java.time.LocalDate;
import java.util.UUID;

public record ForoResponseDTO(
    UUID id,
    String nombreForo,
    String descripcion,
    LocalDate fechaCreacion,
    UUID cursoOfertado
) {}
