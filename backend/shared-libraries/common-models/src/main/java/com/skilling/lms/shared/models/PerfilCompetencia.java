package com.skilling.lms.shared.models;

import java.util.UUID;

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
@Table("perfil_competencia")
public class PerfilCompetencia {

    @Column("perfil_curricular_id")
    private UUID perfilesCurricularesId;
    
    @Column("competencia_id")
    private UUID competenciasId;
}
