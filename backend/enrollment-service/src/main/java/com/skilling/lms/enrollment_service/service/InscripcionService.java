package com.skilling.lms.enrollment_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.enrollment.request.InscripcionRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.InscripcionResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InscripcionService {

    Mono<InscripcionResponseDTO> createInscripcion(InscripcionRequestDTO requestDTO);
    Mono<InscripcionResponseDTO> getInscripcionById(UUID id);
    Flux<InscripcionResponseDTO> getAllInscripciones();
    Mono<InscripcionResponseDTO> updateInscripcion(UUID id, InscripcionRequestDTO requestDTO);
    Mono<Void> deleteInscripcion(UUID id);
}
