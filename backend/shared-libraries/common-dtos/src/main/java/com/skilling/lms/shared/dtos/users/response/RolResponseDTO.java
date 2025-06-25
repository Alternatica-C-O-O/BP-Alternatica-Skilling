package com.skilling.lms.shared.dtos.users.response;

import java.util.Set;
import java.util.UUID;

public record RolResponseDTO(
    UUID id,
    String nombreRol,
    String descripcion,
    Set<PermisoResponseDTO> permisos 
) {}
