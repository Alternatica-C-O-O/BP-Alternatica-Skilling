package com.skilling.lms.shared.dtos.recurso_didactico.request;

import com.skilling.lms.shared.models.enums.ArchivoTipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record RecursoDidacticoRequestDTO(
        @NotNull(message = "El ID es requerido")
        UUID id,

        @NotBlank(message = "El nombre del archivo no puede estar vacío")
        @Size(max = 255, message = "El nombre del archivo no puede exceder los 255 caracteres")
        String nombreArchivo,

        @NotNull(message = "El tipo de archivo es requerido")
        ArchivoTipo tipoArchivo,

        @NotBlank(message = "La URL no puede estar vacía")
        @Size(max = 500, message = "La URL no puede exceder los 500 caracteres")
        String url,

        @Size(max = 1000, message = "Los metadatos no pueden exceder los 1000 caracteres")
        String metadata,

        @NotNull(message = "La versión es requerida")
        Integer version,

        @NotNull(message = "El estado activo es requerido")
        Boolean esActivo,

        @NotNull(message = "El ID de usuario es requerido")
        UUID usuariosId
) {}