package com.skilling.lms.curriculum_service.service;

import java.util.Set;
import java.util.UUID;

import com.skilling.lms.shared.dtos.curriculum.request.CursoOfertadoRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.CursoOfertadoResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CursoOfertadoService {

    Mono<CursoOfertadoResponseDTO> createCursoOfertado(CursoOfertadoRequestDTO requestDTO);
    Mono<CursoOfertadoResponseDTO> getCursoOfertadoById(UUID id);
    Flux<CursoOfertadoResponseDTO> getAllCursosOfertados();
    Mono<CursoOfertadoResponseDTO> updateCursoOfertado(UUID id, CursoOfertadoRequestDTO requestDTO);
    Mono<Void> deleteCursoOfertado(UUID id);

    // Métodos para manejar la relación N:M con Cursos prerequisitos
    Mono<CursoOfertadoResponseDTO> addPrerequisitosToCurso(UUID cursoId, Set<UUID> prerequisitoIds);
    Mono<CursoOfertadoResponseDTO> removePrerequisitosFromCurso(UUID cursoId, Set<UUID> prerequisitoIds);
}
