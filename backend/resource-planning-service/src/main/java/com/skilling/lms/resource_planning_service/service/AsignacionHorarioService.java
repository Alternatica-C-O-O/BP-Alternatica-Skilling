package com.skilling.lms.resource_planning_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.resources_planning.request.AsignacionHorarioRequestDTO;
import com.skilling.lms.shared.dtos.resources_planning.response.AsignacionHorarioResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AsignacionHorarioService {
    
    Mono<AsignacionHorarioResponseDTO> createAsignacionHorario(AsignacionHorarioRequestDTO requestDTO);
    Mono<AsignacionHorarioResponseDTO> getAsignacionHorarioById(UUID id);
    Flux<AsignacionHorarioResponseDTO> getAllAsignacionesHorario();
    Mono<AsignacionHorarioResponseDTO> updateAsignacionHorario(UUID id, AsignacionHorarioRequestDTO requestDTO);
    Mono<Void> deleteAsignacionHorario(UUID id);
}
