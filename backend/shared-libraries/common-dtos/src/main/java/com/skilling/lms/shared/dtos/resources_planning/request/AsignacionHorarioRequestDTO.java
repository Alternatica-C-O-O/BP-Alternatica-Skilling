package com.skilling.lms.shared.dtos.resources_planning.request;

import java.time.LocalTime;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.AsignacionTipo;
import com.skilling.lms.shared.models.enums.DiaSemana;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AsignacionHorarioRequestDTO(
    @NotBlank(message = "El día de la semana no puede estar vacío")
    @Size(max = 150, message = "El día de la semana no puede exceder 150 caracteres")
    DiaSemana diaSemana,

    @NotNull(message = "La hora de inicio no puede ser nula")
    LocalTime horaInicio,

    @NotNull(message = "La hora de fin no puede ser nula")
    LocalTime horaFin,

    @NotNull(message = "El tipo de asignacion no puede ser nulo")
    AsignacionTipo tipoAsignacion,

    @NotNull(message = "El ID de usuario no puede ser nulo")
    UUID usuariosId,

    @NotNull(message = "El ID del espacio físico no puede ser nulo")
    UUID espacioFisicoId,

    @NotNull(message = "El ID de la plataforma virtual no puede ser nulo")
    UUID plataformaVirtualId,

    @NotNull(message = "El ID del curso ofertado no puede ser nulo")
    UUID cursoOfertadoId
) {}
