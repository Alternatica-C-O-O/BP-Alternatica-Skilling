package com.skilling.lms.resource_planning_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.resource_planning.request.AsignacionHorarioRequestDTO;
import com.skilling.lms.shared.dtos.resource_planning.response.AsignacionHorarioResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AsignacionHorarioService {

    Mono<AsignacionHorarioResponseDTO> create(AsignacionHorarioRequestDTO dto);

    Mono<AsignacionHorarioResponseDTO> getById(UUID id);

    Flux<AsignacionHorarioResponseDTO> getAll();

    Mono<AsignacionHorarioResponseDTO> update(UUID id, AsignacionHorarioRequestDTO dto);

    Mono<Void> delete(UUID id);
}
