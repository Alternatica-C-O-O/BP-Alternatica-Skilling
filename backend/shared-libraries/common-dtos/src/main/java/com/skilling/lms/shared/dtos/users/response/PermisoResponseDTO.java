package com.skilling.lms.shared.dtos.users.response;

import java.util.UUID;

public record PermisoResponseDTO(
    UUID id,
    String nombrePermiso,
    String descripcion
) {}
