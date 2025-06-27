package com.skilling.lms.shared.dtos.enrollment.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.AsistenciaEstado;

public record AsistenciaResponseDTO(
    UUID id,
    LocalDate fechaClase,
    AsistenciaEstado estadoAsistencia,
    LocalTime horaRegistro,
    UUID inscripcion 
) {}