package com.skilling.lms.shared.dtos.resources_planning.response;

import java.util.UUID;

import com.skilling.lms.shared.models.enums.PlataformaVirtualTipo;

public record PlataformaVirtualResponseDTO(
    UUID id,
    String nombrePlataforma,
    String url,
    PlataformaVirtualTipo tipo,
    String credencialesApi
) {}