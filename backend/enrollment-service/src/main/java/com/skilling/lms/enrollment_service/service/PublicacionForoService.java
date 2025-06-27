package com.skilling.lms.enrollment_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.enrollment.request.PublicacionForoRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.PublicacionForoResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PublicacionForoService {

    Mono<PublicacionForoResponseDTO> createPublicacionForo(PublicacionForoRequestDTO requestDTO);
    Mono<PublicacionForoResponseDTO> getPublicacionForoById(UUID id);
    Flux<PublicacionForoResponseDTO> getAllPublicacionesForo();
    Mono<PublicacionForoResponseDTO> updatePublicacionForo(UUID id, PublicacionForoRequestDTO requestDTO);
    Mono<Void> deletePublicacionForo(UUID id);
}
