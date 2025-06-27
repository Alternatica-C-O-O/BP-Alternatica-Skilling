package com.skilling.lms.shared.dtos.content.request;

import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.skilling.lms.shared.models.enums.ArchivoTipo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RecursoDidacticoRequestDTO(
    @NotBlank(message = "El nombre del archivo no puede estar vacío")
    @Size(max = 255, message = "El nombre del archivo no puede exceder 255 caracteres")
    String nombreArchivo,

    @NotNull(message = "El tipo de archivo no puede ser nulo")
    ArchivoTipo tipoArchivo,

    @NotBlank(message = "La URL no puede estar vacía")
    @Size(max = 255, message = "La URL no puede exceder 255 caracteres")
    String url,

    @NotNull(message = "Los metadatos no pueden ser nulos")
    JsonNode metadata,

    @NotNull(message = "La versión no puede ser nula")
    @Min(value = 1, message = "La versión debe ser al menos 1")
    Integer version,

    @NotNull(message = "El estado activo no puede ser nulo")
    Boolean esActivo,

    @NotNull(message = "El ID de usuario no puede ser nulo")
    UUID usuariosId
) {}