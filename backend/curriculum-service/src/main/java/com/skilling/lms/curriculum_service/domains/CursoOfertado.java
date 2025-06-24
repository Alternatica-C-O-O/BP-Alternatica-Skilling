package com.skilling.lms.curriculum_service.domains;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.ModalidadTipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("curso_ofertado")
public class CursoOfertado {

    @Id
    @Column("id")
    private UUID id;

    @Column("nombre_curso")
    private String nombreCurso;

    @Column("codigo_curso")
    private String codigoCurso;

    private Integer creditos;
    private String descripcion;

    @Column("duracion_semanas")
    private Integer duracionSemanas;
    
    private ModalidadTipo modalidad;

    @Column("periodo_academico_id")
    private UUID periodoAcademicoId; // Foreign key

    @Column("plan_estudio_id")
    private UUID planEstudioId; // Foreign key
}
