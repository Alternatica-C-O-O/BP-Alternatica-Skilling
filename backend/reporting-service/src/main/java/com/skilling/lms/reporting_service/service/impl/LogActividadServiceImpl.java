package com.skilling.lms.reporting_service.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.reporting_service.domains.LogActividad;
import com.skilling.lms.reporting_service.repositories.LogActividadRepository;
import com.skilling.lms.reporting_service.service.LogActividadService;
import com.skilling.lms.shared.dtos.reporting.request.LogActividadRequestDTO;
import com.skilling.lms.shared.dtos.reporting.response.LogActividadResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogActividadServiceImpl implements LogActividadService {

    private final LogActividadRepository logActividadRepository;

    private static final String LOG_ACTIVIDAD_SERVICE_CB = "logActividadServiceCB";

    @Override
    @CircuitBreaker(name = LOG_ACTIVIDAD_SERVICE_CB, fallbackMethod = "fallbackCreateLogActividad")
    public Mono<LogActividadResponseDTO> createLogActividad(LogActividadRequestDTO requestDTO) {
        LogActividad logActividad = LogActividad.builder()
                .fechaHora(LocalDateTime.now()) 
                .tipoEvento(requestDTO.tipoEvento())
                .detalleEvento(requestDTO.detalleEvento())
                .ipOrigen(requestDTO.ipOrigen())
                .usuariosId(requestDTO.usuariosId())
                .build();

        return logActividadRepository.save(logActividad)
                .map(this::toLogActividadResponseDTO);
    }

    @SuppressWarnings("unused") 
    private Mono<LogActividadResponseDTO> fallbackCreateLogActividad(LogActividadRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createLogActividad activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Log de Actividad no está disponible o falló al crear el registro."));
    }

    @Override
    @CircuitBreaker(name = LOG_ACTIVIDAD_SERVICE_CB, fallbackMethod = "fallbackGetLogActividadById")
    public Mono<LogActividadResponseDTO> getLogActividadById(UUID id) {
        return logActividadRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Log de Actividad no encontrado con ID: " + id)))
                .map(this::toLogActividadResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<LogActividadResponseDTO> fallbackGetLogActividadById(UUID id, Throwable t) {
        log.error("Fallback para getLogActividadById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Log de Actividad no está disponible o falló al obtener el registro."));
    }

    @Override
    @CircuitBreaker(name = LOG_ACTIVIDAD_SERVICE_CB, fallbackMethod = "fallbackGetAllLogsActividad")
    public Flux<LogActividadResponseDTO> getAllLogsActividad() {
        return logActividadRepository.findAll()
                .map(this::toLogActividadResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<LogActividadResponseDTO> fallbackGetAllLogsActividad(Throwable t) {
        log.error("Fallback para getAllLogsActividad activado. Causa: {}", t.getMessage());
        return Flux.empty(); 
    }

    @Override
    @CircuitBreaker(name = LOG_ACTIVIDAD_SERVICE_CB, fallbackMethod = "fallbackDeleteLogActividad")
    public Mono<Void> deleteLogActividad(UUID id) {
        return logActividadRepository.deleteById(id)
                .then(Mono.empty()); 
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteLogActividad(UUID id, Throwable t) {
        log.error("Fallback para deleteLogActividad activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Log de Actividad no está disponible o falló al eliminar el registro."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private LogActividadResponseDTO toLogActividadResponseDTO(LogActividad logActividad) {
        return new LogActividadResponseDTO(
            logActividad.getId(),
            logActividad.getFechaHora(),
            logActividad.getTipoEvento(),
            logActividad.getDetalleEvento(),
            logActividad.getIpOrigen(),
            logActividad.getUsuariosId() 
        );
    }
}
