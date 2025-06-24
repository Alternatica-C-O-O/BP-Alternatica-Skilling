package com.skilling.lms.shared.dtos.create;

import com.skilling.lms.shared.models.enums.UsuarioTipo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateRequestDTO(
    @NotBlank(message = "El nombre no puede estar vacío")
    String nombre,
    @NotBlank(message = "El apellido no puede estar vacío")
    String apellido,
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Formato de email inválido")
    String email,
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    String password,
    @NotNull(message = "El tipo de usuario es requerido")
    UsuarioTipo tipo_usuario
) {}
