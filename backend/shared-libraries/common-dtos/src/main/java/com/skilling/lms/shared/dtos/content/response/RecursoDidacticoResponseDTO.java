package com.skilling.lms.shared.dtos.content.response;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.skilling.lms.shared.models.enums.ArchivoTipo;

public record RecursoDidacticoResponseDTO(
    UUID id,
    String nombreArchivo,
    ArchivoTipo tipoArchivo,
    String url,
    LocalDate fechaSubida,
    JsonNode metadata,
    Integer version,
    Boolean esActivo,
    UUID usuario
) {}