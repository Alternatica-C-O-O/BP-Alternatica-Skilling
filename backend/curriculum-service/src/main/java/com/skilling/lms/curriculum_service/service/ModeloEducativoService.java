package com.skilling.lms.curriculum_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.curriculum.request.ModeloEducativoRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.ModeloEducativoResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ModeloEducativoService {

    Mono<ModeloEducativoResponseDTO> createModeloEducativo(ModeloEducativoRequestDTO requestDTO);
    Mono<ModeloEducativoResponseDTO> getModeloEducativoById(UUID id);
    Flux<ModeloEducativoResponseDTO> getAllModelosEducativos();
    Mono<ModeloEducativoResponseDTO> updateModeloEducativo(UUID id, ModeloEducativoRequestDTO requestDTO);
    Mono<Void> deleteModeloEducativo(UUID id);
}
