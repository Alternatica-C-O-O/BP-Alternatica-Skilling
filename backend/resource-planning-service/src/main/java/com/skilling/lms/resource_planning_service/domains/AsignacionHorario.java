package com.skilling.lms.resource_planning_service.domains;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("asignacion_horario")
public class AsignacionHorario {
	@Id
	@Column("id")
	private UUID id;

	@Column("dia_semana")
	private String diaSemana;

	@Column("hora_inicio")
	private String horaInicio;

	@Column("hora_fin")
	private String horaFin;

	@Column("tipo_asignacion")
	private String tipoAsignacion; // Enum: Clase, Consulta, Laboratorio

	@Column("espacio_fisico_id")
	private UUID espacioFisicoId;

	@Column("plataforma_virtual_id")
	private UUID plataformaVirtualId;
}
