package com.skilling.lms.shared.dtos.curriculum.request;

import java.util.Set;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.ModalidadTipo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CursoOfertadoRequestDTO(
    @NotBlank(message = "El nombre del curso no puede estar vacío")
    @Size(max = 255, message = "El nombre del curso no puede exceder 255 caracteres")
    String nombreCurso,

    @NotBlank(message = "El código del curso no puede estar vacío")
    @Size(max = 150, message = "El código del curso no puede exceder 150 caracteres")
    String codigoCurso,

    @NotNull(message = "Los créditos no pueden ser nulos")
    @Min(value = 0, message = "Los créditos deben ser un número positivo")
    Integer creditos,

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    String descripcion,

    @NotNull(message = "La duración en semanas no puede ser nula")
    @Min(value = 1, message = "La duración debe ser al menos 1 semana")
    Integer duracionSemanas,

    @NotBlank(message = "La modalidad no puede estar vacía")
    @Size(max = 150, message = "La modalidad no puede exceder 150 caracteres")
    ModalidadTipo modalidad,

    @NotNull(message = "El ID del periodo académico no puede ser nulo")
    UUID periodoAcademicoId,

    @NotNull(message = "El ID del plan de estudio no puede ser nulo")
    UUID planEstudioId,

    Set<UUID> prerequisitoIds
) {}
