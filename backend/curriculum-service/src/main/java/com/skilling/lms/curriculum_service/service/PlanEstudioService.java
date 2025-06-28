package com.skilling.lms.curriculum_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.curriculum.request.PlanEstudioRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.PlanEstudioResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanEstudioService {

    Mono<PlanEstudioResponseDTO> createPlanEstudio(PlanEstudioRequestDTO requestDTO);
    Mono<PlanEstudioResponseDTO> getPlanEstudioById(UUID id);
    Flux<PlanEstudioResponseDTO> getAllPlanesEstudio();
    Mono<PlanEstudioResponseDTO> updatePlanEstudio(UUID id, PlanEstudioRequestDTO requestDTO);
    Mono<Void> deletePlanEstudio(UUID id);
}
