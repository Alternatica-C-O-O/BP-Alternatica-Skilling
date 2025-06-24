package com.skilling.lms.assessments_service.domains;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.EvaluacionTipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("evaluacion")
public class Evaluacion {

    @Id
    @Column("id")
    private UUID id;

    @Column("nombre_evaluacion")
    private String nombreEvaluacion;

    @Column("tipo_evaluacion")
    private EvaluacionTipo tipoEvaluacion;

    @Column("porcentaje_calificacion")
    private Double porcentajeCalificacion;

    @Column("fecha_vencimiento")
    private LocalDate fechaVencimiento;

    private String descripcion;
    
    @Column("peso_relativo")
    private Double pesoRelativo;

    @Column("puntaje_maximo")
    private Double puntajeMaximo;

    @Column("curso_ofertado_id")
    private UUID cursoOfertadoId; // Foreign key
    
    @Column("modulo_id")
    private UUID moduloId; // Foreign key
}
