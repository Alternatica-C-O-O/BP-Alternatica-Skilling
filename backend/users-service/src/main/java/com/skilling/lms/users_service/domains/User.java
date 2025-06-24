package com.skilling.lms.users_service.domains;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("usuarios")
public class User {
    
    @Id
    @Column("id")
    private UUID id;

    private String nombre;
    private String apellido;
    private String email;

    @Column("clave_hash")
    private String claveHash;

    @Column("fecha_registro")
    private LocalDateTime fechaRegistro;
    
    @Column("estado_activo")
    private Boolean estadoActivo;

    @Column("tipo_usuario")
    private UserType tipoUsuario;
    
    private Set<Role> roles;
}
