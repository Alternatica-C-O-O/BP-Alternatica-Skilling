package com.skilling.lms.shared.dtos.curriculum.response;

import java.util.UUID;

public record CompetenciaResponseDTO(
    UUID id,
    String nombreCompetencia,
    String descripcion,
    String nivelEsperado
) {}