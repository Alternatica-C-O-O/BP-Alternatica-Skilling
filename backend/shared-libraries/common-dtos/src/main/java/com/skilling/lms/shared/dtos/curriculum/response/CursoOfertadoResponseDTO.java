package com.skilling.lms.shared.dtos.curriculum.response;

import java.util.Set;
import java.util.UUID;

import com.skilling.lms.shared.dtos.resources_planning.response.PeriodoAcademicoResponseDTO;
import com.skilling.lms.shared.models.enums.ModalidadTipo;

public record CursoOfertadoResponseDTO(
    UUID id,
    String nombreCurso,
    String codigoCurso,
    Integer creditos,
    String descripcion,
    Integer duracionSemanas,
    ModalidadTipo modalidad,
    PeriodoAcademicoResponseDTO periodoAcademico, 
    PlanEstudioResponseDTO planEstudio,           
    Set<CursoOfertadoResponseDTO> prerequisitos
) {}