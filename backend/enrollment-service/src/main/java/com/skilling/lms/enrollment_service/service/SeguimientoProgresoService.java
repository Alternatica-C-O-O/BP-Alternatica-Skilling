package com.skilling.lms.enrollment_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.enrollment.request.SeguimientoProgresoRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.SeguimientoProgresoResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SeguimientoProgresoService {

    Mono<SeguimientoProgresoResponseDTO> createSeguimientoProgreso(SeguimientoProgresoRequestDTO requestDTO);
    Mono<SeguimientoProgresoResponseDTO> getSeguimientoProgresoById(UUID id);
    Flux<SeguimientoProgresoResponseDTO> getAllSeguimientosProgreso();
    Mono<SeguimientoProgresoResponseDTO> updateSeguimientoProgreso(UUID id, SeguimientoProgresoRequestDTO requestDTO);
    Mono<Void> deleteSeguimientoProgreso(UUID id);
}
