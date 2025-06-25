package com.skilling.lms.shared.dtos.users.request;

import java.util.Set;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.UsuarioTipo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    String nombre,

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    String apellido,

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe ser un formato de email válido")
    @Size(max = 100, message = "El email no puede exceder 100 caracteres")
    String email,

    @NotBlank(message = "La clave no puede estar vacía")
    @Size(min = 8, message = "La clave debe tener al menos 8 caracteres")
    String clave,

    @NotNull(message = "El tipo de usuario no puede ser nulo")
    UsuarioTipo tipoUsuario,

    Set<UUID> rolIds
) {}
