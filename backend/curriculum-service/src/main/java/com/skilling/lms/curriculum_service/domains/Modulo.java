package com.skilling.lms.curriculum_service.domains;

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
@Table("modulo")
public class Modulo {

    @Id
    private UUID id;

    @Column("nombre_modulo")
    private String nombreModulo;

    private Integer orden;
    private String descripcion;

    @Column("objetivos_aprendizaje")
    private String objetivosAprendizaje;

    @Column("curso_ofertado_id")
    private UUID cursoOfertadoId; // Foreign key
}
