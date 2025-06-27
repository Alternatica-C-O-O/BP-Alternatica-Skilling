package com.skilling.lms.content_service.service;

import com.skilling.lms.shared.dtos.create.RecursoDidacticoCreateRequestDTO;
import com.skilling.lms.shared.dtos.recurso_didactico.request.RecursoDidacticoRequestDTO;
import com.skilling.lms.shared.dtos.recurso_didactico.response.RecursoDidacticoResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RecursoDidacticoService {

    Mono<RecursoDidacticoResponseDTO> createRecurso(RecursoDidacticoCreateRequestDTO requestDTO);
    Mono<RecursoDidacticoResponseDTO> getRecursoById(UUID id);
    Flux<RecursoDidacticoResponseDTO> getAllRecursos();
    Flux<RecursoDidacticoResponseDTO> getRecursosByUsuario(UUID usuariosId);
    Flux<RecursoDidacticoResponseDTO> getRecursosByTipo(String tipo); // si deseas
    Mono<RecursoDidacticoResponseDTO> updateRecurso(UUID id, RecursoDidacticoRequestDTO requestDTO);
    Mono<Void> deleteRecurso(UUID id); // soft delete (esActivo = false)
}