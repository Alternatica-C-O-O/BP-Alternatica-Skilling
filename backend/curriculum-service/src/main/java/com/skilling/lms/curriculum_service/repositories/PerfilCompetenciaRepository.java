package com.skilling.lms.curriculum_service.repositories;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.shared.models.PerfilCompetencia;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PerfilCompetenciaRepository extends ReactiveCrudRepository<PerfilCompetencia, UUID> {

    @Query("SELECT competencia_id FROM perfil_competencia WHERE perfil_curricular_id = :perfilCurricularId")
    Flux<UUID> findCompetenciaIdsByPerfilCurricularId(UUID perfilCurricularId);

    Mono<Void> deleteByPerfilesCurricularesIdAndCompetenciasId(UUID perfilCurricularId, UUID competenciaId);
    Mono<Boolean> existsByPerfilesCurricularesIdAndCompetenciasId(UUID perfilCurricularId, UUID competenciaId);
    Mono<Void> deleteByPerfilesCurricularesId(UUID perfilCurricularId);
}
