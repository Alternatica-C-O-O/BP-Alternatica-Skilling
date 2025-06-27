package com.skilling.lms.shared.dtos.curriculum.response;

import java.util.Set;
import java.util.UUID;

public record PerfilCurricularResponseDTO(
    UUID id,
    String nombrePerfil,
    String descripcion,
    ModeloEducativoResponseDTO modeloEducativo,
    Set<CompetenciaResponseDTO> competencias 
) {}
