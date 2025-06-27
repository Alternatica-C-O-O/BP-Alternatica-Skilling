package com.skilling.lms.reporting_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.reporting.request.ReporteGeneradoRequestDTO;
import com.skilling.lms.shared.dtos.reporting.response.ReporteGeneradoResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReporteGeneradoService {

    Mono<ReporteGeneradoResponseDTO> createReporteGenerado(ReporteGeneradoRequestDTO requestDTO);
    Mono<ReporteGeneradoResponseDTO> getReporteGeneradoById(UUID id);
    Flux<ReporteGeneradoResponseDTO> getAllReportesGenerados();
    Mono<ReporteGeneradoResponseDTO> updateReporteGenerado(UUID id, ReporteGeneradoRequestDTO requestDTO);
    Mono<Void> deleteReporteGenerado(UUID id);
}
