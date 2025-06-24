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
@Table("espacio_fisico")
public class EspacioFisico {
	@Id
	@Column("id")
	private UUID id;

	@Column("nombre")
	private String nombre;

	@Column("capacidad")
	private Integer capacidad;

	@Column("tipo_espacio")
	private String tipoEspacio; // Ejemplo: Recepción, Laboratorio, Sala de cómputo, etc

	@Column("ubicacion")
	private String ubicacion; // Dirección
}
