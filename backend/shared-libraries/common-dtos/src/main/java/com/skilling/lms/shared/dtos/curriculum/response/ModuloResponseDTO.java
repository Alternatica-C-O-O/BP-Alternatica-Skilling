package com.skilling.lms.shared.dtos.curriculum.response;

import java.util.UUID;

public record ModuloResponseDTO(
    UUID id,
    String nombreModulo,
    Integer orden,
    String descripcion,
    String objetivosAprendizaje,
    CursoOfertadoResponseDTO cursoOfertado
) {}