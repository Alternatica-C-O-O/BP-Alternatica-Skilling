package com.skilling.lms.assessments_service.domains;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("calificacion")
public class Calificacion {

    @Id
    @Column("id")
    private UUID id;

    @Column("puntaje_obtenido")
    private Double puntajeObtenido;

    @Column("fecha_calificacion")
    private LocalDate fechaCalificacion;

    @Column("comentarios_docente")
    private String comentariosDocente;
    
    @Column("inscripcion_id")
    private UUID inscripcionId; // Foreign key
    
    @Column("evaluacion_id")
    private UUID evaluacionId; // Foreign key
}
