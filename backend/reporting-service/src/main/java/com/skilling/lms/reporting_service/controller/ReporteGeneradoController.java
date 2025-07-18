package com.skilling.lms.reporting_service.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.skilling.lms.reporting_service.service.ReporteGeneradoService;
import com.skilling.lms.shared.dtos.reporting.request.ReporteGeneradoRequestDTO;
import com.skilling.lms.shared.dtos.reporting.response.ReporteGeneradoResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reportes-generados")
public class ReporteGeneradoController {

    private final ReporteGeneradoService reporteGeneradoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<ReporteGeneradoResponseDTO> createReporteGenerado(@Valid @RequestBody ReporteGeneradoRequestDTO requestDTO) {
        return reporteGeneradoService.createReporteGenerado(requestDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<ReporteGeneradoResponseDTO> getAllReportesGenerados() {
        return reporteGeneradoService.getAllReportesGenerados();
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<ReporteGeneradoResponseDTO> getReporteGeneradoById(@PathVariable UUID id) {
        return reporteGeneradoService.getReporteGeneradoById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporte generado no encontrado")));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<ReporteGeneradoResponseDTO> updateReporteGenerado(@PathVariable UUID id, @Valid @RequestBody ReporteGeneradoRequestDTO requestDTO) {
        return reporteGeneradoService.updateReporteGenerado(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporte generado no encontrado para actualizar")));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteReporteGenerado(@PathVariable UUID id) {
        return reporteGeneradoService.deleteReporteGenerado(id);
    }
}
