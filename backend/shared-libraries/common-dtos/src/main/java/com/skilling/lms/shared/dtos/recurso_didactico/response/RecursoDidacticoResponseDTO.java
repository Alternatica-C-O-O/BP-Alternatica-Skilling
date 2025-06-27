package com.skilling.lms.shared.dtos.recurso_didactico.response;

import com.skilling.lms.shared.models.enums.ArchivoTipo;

import java.time.LocalDate;
import java.util.UUID;

public record RecursoDidacticoResponseDTO(
        UUID id,
        String nombreArchivo,
        String tipoArchivo, // Cambiado a String para mejor compatibilidad con frontend
        String url,
        LocalDate fechaSubida,
        String metadata,
        Integer version,
        Boolean esActivo,
        UUID usuariosId
) {}
