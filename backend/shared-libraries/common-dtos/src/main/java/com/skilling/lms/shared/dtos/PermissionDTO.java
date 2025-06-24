package com.skilling.lms.shared.dtos;

import java.util.UUID;

public record PermissionDTO(
    UUID permiso_id,
    String nombre_permiso,
    String descripcion
) {}
