package com.skilling.lms.shared.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.UsuarioTipo;

public record UserDTO(
    UUID usuario_id,
    String nombre,
    String apellido,
    String email, 
    LocalDateTime fecha_registro,
    Boolean estado_activo,
    UsuarioTipo tipo_usuario
) {}
