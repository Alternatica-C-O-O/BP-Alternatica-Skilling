package com.skilling.lms.users_service.domains;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("permisos")
public class Permission {

    @Id
    @Column("id")
    private UUID id;

    @Column("nombre_permiso")
    private String nombrePermiso;
    private String descripcion;
}
