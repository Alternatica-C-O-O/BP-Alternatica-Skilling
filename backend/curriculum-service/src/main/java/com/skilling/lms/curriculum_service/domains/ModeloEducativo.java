package com.skilling.lms.curriculum_service.domains;

import java.time.LocalDate;
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
@Table("modelo_educativo")
public class ModeloEducativo {

    @Id
    @Column("id")
    private UUID id;

    @Column("nombre_modelo")
    private String nombreModelo;

    private String descripcion;
    private String version;

    @Column("fecha_creacion")
    private LocalDate fechaCreacion;

    private String estado;

    @Column("usuarios_id")
    private UUID usuariosId; // Foreign key (creador/editor)
}
