package com.skilling.lms.resource_planning_service.domains;

import java.time.LocalTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.AsignacionTipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("asignacion_horario")
public class AsignacionHorario {

    @Id
    @Column("id")
    private UUID id;

    @Column("dia_semana")
    private Integer diaSemana;

    @Column("hora_inicio")
    private LocalTime horaInicio;

    @Column("hora_fin")
    private LocalTime horaFin;

    @Column("tipo_asignacion")
    private AsignacionTipo tipoAsignacion;

    @Column("usuarios_id")
    private UUID usuariosId; // Foreign key (profesor/personal)

    @Column("espacio_fisico_id")
    private UUID espacioFisicoId; // Foreign key

    @Column("plataforma_virtual_id")
    private UUID plataformaVirtualId; // Foreign key

    @Column("curso_ofertado_id")
    private UUID cursoOfertadoId; // Foreign key

    @Column("periodo_academico_id")
    private UUID periodoAcademicoId; // Foreign key
}
