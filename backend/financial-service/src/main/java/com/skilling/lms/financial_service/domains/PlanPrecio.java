package com.skilling.lms.financial_service.domains;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.PlanPrecioTipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("plan_precio")
public class PlanPrecio {

    @Id
    @Column("id")
    private UUID id;

    @Column("nombre_plan")
    private String nombrePlan;

    private String version;

    @Column("fecha_aprobacion")
    private LocalDate fechaAprobacion;

    private PlanPrecioTipo estado;

    @Column("fecha_ultima_actualizacion")
    private LocalDate fechaUltimaActualizacion;
    
    @Column("curso_ofertado_id")
    private UUID cursoOfertadoId; // Foreign key
}
