package com.skilling.lms.shared.models;

import org.springframework.data.annotation.Id;
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

    @Id
    private PerfilCompetenciaId id;

    // @Transient private PerfilCurricular perfilCurricular;
    // @Transient private Competencia competencia;
}
