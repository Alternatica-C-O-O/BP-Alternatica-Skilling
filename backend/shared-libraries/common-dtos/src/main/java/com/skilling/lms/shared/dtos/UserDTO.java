package com.skilling.lms.shared.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.UserType;

public record UserDTO(
    UUID usuario_id,
    String nombre,
    String apellido,
    String email, 
    LocalDateTime fecha_registro,
    Boolean estado_activo,
    UserType tipo_usuario
) {}
