package com.skilling.lms.shared.dtos;

import java.util.UUID;

public record RoleDTO(
    UUID role_id,
    String nombre_rol,
    String descripcion
) {}
