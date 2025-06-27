package com.skilling.lms.shared.dtos.resources_planning.response;

import java.time.LocalTime;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.AsignacionTipo;
import com.skilling.lms.shared.models.enums.DiaSemana;

public record AsignacionHorarioResponseDTO(
    UUID id,
    DiaSemana diaSemana,
    LocalTime horaInicio,
    LocalTime horaFin,
    AsignacionTipo tipoAsignacion,
    UUID usuario,        
    UUID espacioFisico,   
    UUID plataformaVirtual,
    UUID cursoOfertado 
) {}