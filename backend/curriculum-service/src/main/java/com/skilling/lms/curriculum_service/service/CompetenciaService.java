package com.skilling.lms.curriculum_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.curriculum.request.CompetenciaRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.CompetenciaResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompetenciaService {

    Mono<CompetenciaResponseDTO> createCompetencia(CompetenciaRequestDTO requestDTO);
    Mono<CompetenciaResponseDTO> getCompetenciaById(UUID id);
    Flux<CompetenciaResponseDTO> getAllCompetencias();
    Mono<CompetenciaResponseDTO> updateCompetencia(UUID id, CompetenciaRequestDTO requestDTO);
    Mono<Void> deleteCompetencia(UUID id);
}
