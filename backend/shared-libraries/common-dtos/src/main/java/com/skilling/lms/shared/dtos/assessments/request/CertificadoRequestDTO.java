package com.skilling.lms.shared.dtos.assessments.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CertificadoRequestDTO(
    @NotBlank(message = "La URL del certificado no puede estar vacía")
    @Size(max = 255, message = "La URL del certificado no puede exceder 255 caracteres")
    String urlCertificado,

    @NotBlank(message = "El código de verificación no puede estar vacío")
    @Size(max = 255, message = "El código de verificación no puede exceder 255 caracteres")
    String codigoVerificacion,

    @NotNull(message = "El ID de inscripción no puede ser nulo")
    UUID inscripcionId
) {}