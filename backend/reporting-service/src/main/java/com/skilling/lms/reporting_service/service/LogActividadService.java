package com.skilling.lms.reporting_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.reporting.request.LogActividadRequestDTO;
import com.skilling.lms.shared.dtos.reporting.response.LogActividadResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LogActividadService {

    Mono<LogActividadResponseDTO> createLogActividad(LogActividadRequestDTO requestDTO);
    Mono<LogActividadResponseDTO> getLogActividadById(UUID id);
    Flux<LogActividadResponseDTO> getAllLogsActividad();
    Mono<Void> deleteLogActividad(UUID id);
}
