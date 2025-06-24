package com.skilling.lms.shared.dtos.update;

import java.util.Optional;

import com.skilling.lms.shared.models.enums.UsuarioTipo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateRequestDTO(
    Optional<String> nombre,
    Optional<String> apellido,
    Optional<@Email(message = "Formato de email inválido") String> email,
    Optional<@Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres") String> password,
    Optional<Boolean> estado_activo,
    Optional<UsuarioTipo> tipo_usuario
) {}
