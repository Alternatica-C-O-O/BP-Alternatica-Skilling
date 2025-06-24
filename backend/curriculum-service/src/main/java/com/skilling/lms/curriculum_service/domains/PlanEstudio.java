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
@Table("plan_estudio")
public class PlanEstudio {

    @Id
    @Column("id")
    private UUID id;

    @Column("nombre_plan")
    private String nombrePlan;

    private String version;

    @Column("fecha_aprobacion")
    private LocalDate fechaAprobacion;

    private String estado;

    @Column("fecha_ultima_actualizacion")
    private LocalDate fechaUltimaActualizacion;
    
    @Column("modelo_educativo_id")
    private UUID modeloEducativoId; // Foreign key

}
