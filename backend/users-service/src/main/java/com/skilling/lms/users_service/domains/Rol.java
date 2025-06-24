package com.skilling.lms.users_service.domains;

import java.util.UUID;

import org.springframework.data.annotation.Id;
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
@Table("roles")
public class Rol {

    @Id
    @Column("id")
    private UUID id;

    @Column("nombre_rol")
    private String nombreRol;
    private String descripcion;
}
