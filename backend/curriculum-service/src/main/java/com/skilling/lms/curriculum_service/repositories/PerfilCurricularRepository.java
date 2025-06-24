package com.skilling.lms.curriculum_service.repositories;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.curriculum_service.domains.PerfilCurricular;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PerfilCurricularRepository extends ReactiveCrudRepository<PerfilCurricular, UUID> {

    Mono<PerfilCurricular> findByNombrePerfil(String nombrePerfil);
    Flux<PerfilCurricular> findByModeloEducativoId(UUID modeloEducativoId);

    public interface PerfilCurricularWithCompetenciasProjection {
        UUID getId();
        @Column("nombre_perfil") String getNombrePerfil();
        @Column("descripcion_perfil") String getDescripcionPerfil();
        @Column("modelo_educativo_id") UUID getModeloEducativoId();
        @Column("competencia_id") UUID getCompetenciaId();
        @Column("nombre_competencia") String getNombreCompetencia();
        @Column("descripcion_competencia") String getDescripcionCompetencia();
        @Column("nivel_esperado") String getNivelEsperado();
    }

    @Query("SELECT pc.id, pc.nombre_perfil, pc.descripcion as descripcion_perfil, pc.modelo_educativo_id, " +
           "c.id as competencia_id, c.nombre_competencia, c.descripcion as descripcion_competencia, c.nivel_esperado " +
           "FROM perfil_curricular pc " +
           "LEFT JOIN perfil_competencia pcomp ON pc.id = pcomp.perfil_curricular_id " +
           "LEFT JOIN competencia c ON pcomp.competencia_id = c.id " +
           "WHERE pc.id = :perfilId")
    Flux<PerfilCurricularWithCompetenciasProjection> findPerfilCurricularWithCompetenciasById(UUID perfilId);
}
