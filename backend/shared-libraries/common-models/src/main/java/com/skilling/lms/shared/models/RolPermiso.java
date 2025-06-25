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
@Table("rol_permiso")
public class RolPermiso {

    @Column("roles_id")
    private UUID rolesId;
    
    @Column("permisos_id")
    private UUID permisosId;
}
