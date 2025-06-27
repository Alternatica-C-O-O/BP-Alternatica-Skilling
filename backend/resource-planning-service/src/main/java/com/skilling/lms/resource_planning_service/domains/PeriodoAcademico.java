package com.skilling.lms.resource_planning_service.domains;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.PeriodoAcademicoEstado;
import com.skilling.lms.shared.models.enums.PeriodoAcademicoTipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("periodo_academico")
public class PeriodoAcademico {

    @Id
	@Column("id")
    private UUID id;

    private String nombre;

    @Column("fecha_inicio")
    private LocalDate fechaInicio;

    @Column("fecha_fin")
    private LocalDate fechaFin;

    private PeriodoAcademicoEstado estado;

    @Column("tipo_periodo")
    private PeriodoAcademicoTipo tipoPeriodo;
}
