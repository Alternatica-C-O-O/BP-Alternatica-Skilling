package com.skilling.lms.curriculum_service.service;

import java.util.Set;
import java.util.UUID;

import com.skilling.lms.shared.dtos.curriculum.request.PerfilCurricularRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.PerfilCurricularResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PerfilCurricularService {

    Mono<PerfilCurricularResponseDTO> createPerfilCurricular(PerfilCurricularRequestDTO requestDTO);
    Mono<PerfilCurricularResponseDTO> getPerfilCurricularById(UUID id);
    Flux<PerfilCurricularResponseDTO> getAllPerfilesCurriculares();
    Mono<PerfilCurricularResponseDTO> updatePerfilCurricular(UUID id, PerfilCurricularRequestDTO requestDTO);
    Mono<Void> deletePerfilCurricular(UUID id);

    // Métodos para manejar la relación N:M con Competencias
    Mono<PerfilCurricularResponseDTO> assignCompetenciasToPerfilCurricular(UUID perfilCurricularId, Set<UUID> competenciaIds);
    Mono<PerfilCurricularResponseDTO> removeCompetenciasFromPerfilCurricular(UUID perfilCurricularId, Set<UUID> competenciaIds);
}
