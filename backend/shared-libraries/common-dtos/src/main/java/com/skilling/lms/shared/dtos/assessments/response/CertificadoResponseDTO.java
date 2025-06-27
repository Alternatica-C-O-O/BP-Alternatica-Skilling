package com.skilling.lms.shared.dtos.assessments.response;

import java.time.LocalDate;
import java.util.UUID;

public record CertificadoResponseDTO(
    UUID id,
    LocalDate fechaEmision,
    String urlCertificado,
    String codigoVerificacion,
    UUID inscripcion
) {}