package com.skilling.lms.reporting_service.service.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.reporting_service.domains.ReporteGenerado;
import com.skilling.lms.reporting_service.repositories.ReporteGeneradoRepository;
import com.skilling.lms.reporting_service.service.ReporteGeneradoService;
import com.skilling.lms.shared.dtos.reporting.request.ReporteGeneradoRequestDTO;
import com.skilling.lms.shared.dtos.reporting.response.ReporteGeneradoResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReporteGeneradoServiceImpl implements ReporteGeneradoService {
    
    private final ReporteGeneradoRepository reporteGeneradoRepository;

    private static final String REPORTE_GENERADO_SERVICE_CB = "reporteGeneradoServiceCB";

    @Override
    @CircuitBreaker(name = REPORTE_GENERADO_SERVICE_CB, fallbackMethod = "fallbackCreateReporteGenerado")
    public Mono<ReporteGeneradoResponseDTO> createReporteGenerado(ReporteGeneradoRequestDTO requestDTO) {
        ReporteGenerado reporteGenerado = ReporteGenerado.builder()
                .nombreReporte(requestDTO.nombreReporte())
                .fechaGeneracion(LocalDate.now()) 
                .parametrosReporte(requestDTO.parametrosReporte())
                .urlReporte(requestDTO.urlReporte())
                .usuariosId(requestDTO.usuariosId())
                .build();

        return reporteGeneradoRepository.save(reporteGenerado)
                .map(this::toReporteGeneradoResponseDTO);
    }

    @SuppressWarnings("unused") 
    private Mono<ReporteGeneradoResponseDTO> fallbackCreateReporteGenerado(ReporteGeneradoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createReporteGenerado activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Reporte Generado no está disponible o falló al crear el reporte."));
    }

    @Override
    @CircuitBreaker(name = REPORTE_GENERADO_SERVICE_CB, fallbackMethod = "fallbackGetReporteGeneradoById")
    public Mono<ReporteGeneradoResponseDTO> getReporteGeneradoById(UUID id) {
        return reporteGeneradoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Reporte Generado no encontrado con ID: " + id)))
                .map(this::toReporteGeneradoResponseDTO); 
    }

    @SuppressWarnings("unused")
    private Mono<ReporteGeneradoResponseDTO> fallbackGetReporteGeneradoById(UUID id, Throwable t) {
        log.error("Fallback para getReporteGeneradoById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Reporte Generado no está disponible o falló al obtener el reporte."));
    }

    @Override
    @CircuitBreaker(name = REPORTE_GENERADO_SERVICE_CB, fallbackMethod = "fallbackGetAllReportesGenerados")
    public Flux<ReporteGeneradoResponseDTO> getAllReportesGenerados() {
        return reporteGeneradoRepository.findAll()
                .map(this::toReporteGeneradoResponseDTO); 
    }

    @SuppressWarnings("unused")
    private Flux<ReporteGeneradoResponseDTO> fallbackGetAllReportesGenerados(Throwable t) {
        log.error("Fallback para getAllReportesGenerados activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = REPORTE_GENERADO_SERVICE_CB, fallbackMethod = "fallbackUpdateReporteGenerado")
    public Mono<ReporteGeneradoResponseDTO> updateReporteGenerado(UUID id, ReporteGeneradoRequestDTO requestDTO) {
        return reporteGeneradoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Reporte Generado no encontrado para actualizar con ID: " + id)))
                .flatMap(existingReporteGenerado -> {
                    existingReporteGenerado.setNombreReporte(requestDTO.nombreReporte());
                    existingReporteGenerado.setParametrosReporte(requestDTO.parametrosReporte());
                    existingReporteGenerado.setUrlReporte(requestDTO.urlReporte());
                    existingReporteGenerado.setUsuariosId(requestDTO.usuariosId());
                    
                    return reporteGeneradoRepository.save(existingReporteGenerado);
                })
                .map(this::toReporteGeneradoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<ReporteGeneradoResponseDTO> fallbackUpdateReporteGenerado(UUID id, ReporteGeneradoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateReporteGenerado activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Reporte Generado no está disponible o falló al actualizar el reporte."));
    }

    @Override
    @CircuitBreaker(name = REPORTE_GENERADO_SERVICE_CB, fallbackMethod = "fallbackDeleteReporteGenerado")
    public Mono<Void> deleteReporteGenerado(UUID id) {
        return reporteGeneradoRepository.deleteById(id)
                .then(Mono.empty()); 
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteReporteGenerado(UUID id, Throwable t) {
        log.error("Fallback para deleteReporteGenerado activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Reporte Generado no está disponible o falló al eliminar el reporte."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private ReporteGeneradoResponseDTO toReporteGeneradoResponseDTO(ReporteGenerado reporteGenerado) {
        return new ReporteGeneradoResponseDTO(
            reporteGenerado.getId(),
            reporteGenerado.getNombreReporte(),
            reporteGenerado.getFechaGeneracion(),
            reporteGenerado.getParametrosReporte(),
            reporteGenerado.getUrlReporte(),
            reporteGenerado.getUsuariosId()
        );
    }

}
