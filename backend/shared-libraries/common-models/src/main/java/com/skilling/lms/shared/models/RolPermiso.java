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
@Table("rol_permiso")
public class RolPermiso {

    @Id 
    private RolPermisoId id;

    // @Transient private Rol rol;
    // @Transient private Permiso permiso;
}
