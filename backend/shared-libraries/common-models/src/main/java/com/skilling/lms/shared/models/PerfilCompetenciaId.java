package com.skilling.lms.shared.models;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilCompetenciaId implements Serializable {

    @Column("perfil_curricular_id")
    private UUID perfilCurricularId;
    
    @Column("competencia_id")
    private UUID competenciaId;
}
