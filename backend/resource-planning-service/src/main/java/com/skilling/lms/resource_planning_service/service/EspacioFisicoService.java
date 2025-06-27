package com.skilling.lms.resource_planning_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.resources_planning.request.EspacioFisicoRequestDTO;
import com.skilling.lms.shared.dtos.resources_planning.response.EspacioFisicoResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EspacioFisicoService {

    Mono<EspacioFisicoResponseDTO> createEspacioFisico(EspacioFisicoRequestDTO requestDTO);
    Mono<EspacioFisicoResponseDTO> getEspacioFisicoById(UUID id);
    Flux<EspacioFisicoResponseDTO> getAllEspaciosFisicos();
    Mono<EspacioFisicoResponseDTO> updateEspacioFisico(UUID id, EspacioFisicoRequestDTO requestDTO);
    Mono<Void> deleteEspacioFisico(UUID id);
}
