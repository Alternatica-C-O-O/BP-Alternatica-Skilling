package com.skilling.lms.shared.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("usuario_role")
public class UsuarioRole {

    @Id
    private UsuarioRoleId id;
    
    // @Transient private Usuario usuario;
    // @Transient private Rol rol;
}
