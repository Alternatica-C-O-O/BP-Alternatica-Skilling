package com.skilling.lms.content_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.content.request.RecursoDidacticoRequestDTO;
import com.skilling.lms.shared.dtos.content.response.RecursoDidacticoResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecursoDidacticoService {

    Mono<RecursoDidacticoResponseDTO> createRecursoDidactico(RecursoDidacticoRequestDTO requestDTO);
    Mono<RecursoDidacticoResponseDTO> getRecursoDidacticoById(UUID id);
    Flux<RecursoDidacticoResponseDTO> getAllRecursosDidacticos();
    Mono<RecursoDidacticoResponseDTO> updateRecursoDidactico(UUID id, RecursoDidacticoRequestDTO requestDTO);
    Mono<Void> deleteRecursoDidactico(UUID id);
}
