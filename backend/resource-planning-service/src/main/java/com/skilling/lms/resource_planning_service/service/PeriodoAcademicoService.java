package com.skilling.lms.resource_planning_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.resource_planning.request.PeriodoAcademicoRequestDTO;
import com.skilling.lms.shared.dtos.resource_planning.response.PeriodoAcademicoResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PeriodoAcademicoService {
    Mono<PeriodoAcademicoResponseDTO> createPeriodoAcademico(PeriodoAcademicoRequestDTO requestDTO);

    Mono<PeriodoAcademicoResponseDTO> getPeriodoAcademicoById(UUID id);

    Flux<PeriodoAcademicoResponseDTO> getAllPeriodosAcademicos();

    Mono<PeriodoAcademicoResponseDTO> updatePeriodoAcademico(UUID id, PeriodoAcademicoRequestDTO requestDTO);

    Mono<Void> deletePeriodoAcademico(UUID id);
}
