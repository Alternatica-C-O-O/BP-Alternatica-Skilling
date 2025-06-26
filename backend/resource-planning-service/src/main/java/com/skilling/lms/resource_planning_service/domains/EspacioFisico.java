package com.skilling.lms.resource_planning_service.domains;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.EspacioTipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("espacio_fisico")
public class EspacioFisico {

    @Id
    @Column("id")
    private UUID id;

    private String nombre;
    private Integer capacidad;

    @Column("tipo_espacio")
    private EspacioTipo tipoEspacio;

    private String ubicacion;
}
