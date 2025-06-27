package com.skilling.lms.assessments_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.assessments.request.EvaluacionRequestDTO;
import com.skilling.lms.shared.dtos.assessments.response.EvaluacionResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EvaluacionService {
    
    Mono<EvaluacionResponseDTO> createEvaluacion(EvaluacionRequestDTO requestDTO);
    Mono<EvaluacionResponseDTO> getEvaluacionById(UUID id);
    Flux<EvaluacionResponseDTO> getAllEvaluaciones();
    Mono<EvaluacionResponseDTO> updateEvaluacion(UUID id, EvaluacionRequestDTO requestDTO);
    Mono<Void> deleteEvaluacion(UUID id);
}
