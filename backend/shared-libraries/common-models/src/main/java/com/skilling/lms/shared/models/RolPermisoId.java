package com.skilling.lms.shared.models;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/// CLASE:
/// Clave Primaria Compuesta

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolPermisoId implements Serializable {

    @Column("roles_id")
    private UUID rolesId;
    
    @Column("permisos_id")
    private UUID permisosId;
}
