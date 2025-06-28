package com.skilling.lms.shared.dtos.curriculum.response;

import java.time.LocalDate;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.GeneralEstado;

public record ModeloEducativoResponseDTO(
    UUID id,
    String nombreModelo,
    String descripcion,
    String version,
    LocalDate fechaCreacion,
    GeneralEstado estado,
    UUID usuario
) {}
