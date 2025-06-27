package com.skilling.lms.reporting_service.controller;

import com.skilling.lms.reporting_service.dto.request.ReporteGeneradoRequest;
import com.skilling.lms.reporting_service.dto.response.ReporteGeneradoResponse;
import com.skilling.lms.reporting_service.service.ReporteGeneradoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reportes")
public class ReporteGeneradoController {
    private final ReporteGeneradoService reporteGeneradoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<ReporteGeneradoResponse> createReporteGenerado(@Valid @RequestBody ReporteGeneradoRequest request) {
        return reporteGeneradoService.createReporteGenerado(request);
    }
}
