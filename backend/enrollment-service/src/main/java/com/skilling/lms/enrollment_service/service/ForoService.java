package com.skilling.lms.enrollment_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.enrollment.request.ForoRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.ForoResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ForoService {

    Mono<ForoResponseDTO> createForo(ForoRequestDTO requestDTO);
    Mono<ForoResponseDTO> getForoById(UUID id);
    Flux<ForoResponseDTO> getAllForos();
    Mono<ForoResponseDTO> updateForo(UUID id, ForoRequestDTO requestDTO);
    Mono<Void> deleteForo(UUID id);
}
