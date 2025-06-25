package com.skilling.lms.shared.dtos.users.response;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.UsuarioTipo;

public record UsuarioResponseDTO(
    UUID id,
    String nombre,
    String apellido,
    String email,
    LocalDateTime fechaRegistro,
    Boolean estadoActivo,
    UsuarioTipo tipoUsuario,
    Set<RolResponseDTO> roles
) {}
