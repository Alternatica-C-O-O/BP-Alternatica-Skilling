package com.skilling.lms.curriculum_service.domains;

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
@Table("competencia")
public class Competencia {

    @Id
    @Column("id")
    private UUID id;

    @Column("nombre_competencia")
    private String nombreCompetencia;

    private String descripcion;

    @Column("nivel_esperado")
    private String nivelEsperado;
}
