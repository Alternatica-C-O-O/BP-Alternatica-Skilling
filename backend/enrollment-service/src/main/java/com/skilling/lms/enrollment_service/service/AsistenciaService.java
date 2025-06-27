package com.skilling.lms.enrollment_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.enrollment.request.AsistenciaRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.AsistenciaResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AsistenciaService {

    Mono<AsistenciaResponseDTO> createAsistencia(AsistenciaRequestDTO requestDTO);
    Mono<AsistenciaResponseDTO> getAsistenciaById(UUID id);
    Flux<AsistenciaResponseDTO> getAllAsistencias();
    Mono<AsistenciaResponseDTO> updateAsistencia(UUID id, AsistenciaRequestDTO requestDTO);
    Mono<Void> deleteAsistencia(UUID id);
}
