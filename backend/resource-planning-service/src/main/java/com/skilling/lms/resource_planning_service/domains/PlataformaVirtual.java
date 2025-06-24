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
@Table("plataforma_virtual")
public class PlataformaVirtual {
	@Id
	@Column("id")
	private UUID id;

	@Column("nombre_plataforma")
	private String nombrePlataforma;

	@Column("url")
	private String url;

	@Column("tipo")
	private String tipo;

	@Column("credenciales_api")
	private String credencialesApi; // Sensitive
}
