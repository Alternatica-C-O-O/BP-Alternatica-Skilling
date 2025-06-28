package com.skilling.lms.curriculum_service.repositories;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.shared.models.CursoPrerequisito;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CursoPrerequisitoRepository extends ReactiveCrudRepository<CursoPrerequisito, UUID> {

    @Query("SELECT prerequisito_id FROM curso_prerequisito WHERE curso_id = :cursoId")
    Flux<UUID> findPrerequisitoIdsByCursoId(UUID cursoId);

    @Query("SELECT curso_id FROM curso_prerequisito WHERE prerequisito_id = :prerequisitoId")
    Flux<UUID> findCursoIdsByPrerequisitoId(UUID prerequisitoId);

    Mono<Void> deleteByCursoIdAndPrerequisitoId(UUID cursoId, UUID prerequisitoId);
    Mono<Boolean> existsByCursoIdAndPrerequisitoId(UUID cursoId, UUID prerequisitoId);
    Mono<Void> deleteByCursoId(UUID cursoId);
    Mono<Void> deleteByPrerequisitoId(UUID prerequisitoId);
}
