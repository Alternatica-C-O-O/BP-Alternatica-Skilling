package com.skilling.lms.reporting_service.service;

import com.skilling.lms.reporting_service.dto.request.ReporteGeneradoRequest;
import com.skilling.lms.reporting_service.dto.response.ReporteGeneradoResponse;
import reactor.core.publisher.Mono;

public interface ReporteGeneradoService {
    Mono<ReporteGeneradoResponse> createReporteGenerado(ReporteGeneradoRequest request);
}
