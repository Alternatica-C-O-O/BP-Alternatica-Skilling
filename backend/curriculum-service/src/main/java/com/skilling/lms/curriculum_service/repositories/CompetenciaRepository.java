package com.skilling.lms.curriculum_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.curriculum_service.domains.Competencia;

import reactor.core.publisher.Mono;

public interface CompetenciaRepository extends ReactiveCrudRepository<Competencia, UUID> {
    
    Mono<Competencia> findByNombreCompetencia(String nombreCompetencia);
}
