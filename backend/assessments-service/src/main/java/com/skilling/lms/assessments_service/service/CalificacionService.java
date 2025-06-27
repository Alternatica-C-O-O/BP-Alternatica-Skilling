package com.skilling.lms.assessments_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.assessments.request.CalificacionRequestDTO;
import com.skilling.lms.shared.dtos.assessments.response.CalificacionResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CalificacionService {

    Mono<CalificacionResponseDTO> createCalificacion(CalificacionRequestDTO requestDTO);
    Mono<CalificacionResponseDTO> getCalificacionById(UUID id);
    Flux<CalificacionResponseDTO> getAllCalificaciones();
    Mono<CalificacionResponseDTO> updateCalificacion(UUID id, CalificacionRequestDTO requestDTO);
    Mono<Void> deleteCalificacion(UUID id);
}
