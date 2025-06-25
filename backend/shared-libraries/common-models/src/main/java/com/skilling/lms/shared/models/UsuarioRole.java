package com.skilling.lms.shared.models;

import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;
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

    @Column("usuarios_id")
    private UUID usuariosId;

    @Column("roles_id")
    private UUID rolesId;

}
