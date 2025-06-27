package com.skilling.lms.enrollment_service.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.enrollment_service.domains.SeguimientoProgreso;
import com.skilling.lms.enrollment_service.repositories.SeguimientoProgresoRepository;
import com.skilling.lms.enrollment_service.service.SeguimientoProgresoService;
import com.skilling.lms.shared.dtos.enrollment.request.SeguimientoProgresoRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.SeguimientoProgresoResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeguimientoProgresoServiceImpl implements SeguimientoProgresoService {
    
    private final SeguimientoProgresoRepository seguimientoProgresoRepository;

    private static final String SEGUIMIENTO_PROGRESO_SERVICE_CB = "seguimientoProgresoServiceCB";

    @Override
    @CircuitBreaker(name = SEGUIMIENTO_PROGRESO_SERVICE_CB, fallbackMethod = "fallbackCreateSeguimientoProgreso")
    public Mono<SeguimientoProgresoResponseDTO> createSeguimientoProgreso(SeguimientoProgresoRequestDTO requestDTO) {
        return seguimientoProgresoRepository.findByInscripcionIdAndModuloId(requestDTO.inscripcionId(), requestDTO.moduloId())
                .flatMap(existingSeguimiento -> {
                    log.warn("Intento de crear seguimiento de progreso duplicado para Inscripción ID: {} y Módulo ID: {}",
                            requestDTO.inscripcionId(), requestDTO.moduloId());
                    return Mono.error(new RuntimeException("Ya existe un seguimiento de progreso para esta inscripción y módulo."));
                })
                .then(Mono.defer(() -> {
                    SeguimientoProgreso seguimiento = SeguimientoProgreso.builder()
                            .fechaUltimoAcceso(LocalDateTime.now()) 
                            .completado(requestDTO.completado())
                            .puntajeObtenidoModulo(requestDTO.puntajeObtenidoModulo())
                            .inscripcionId(requestDTO.inscripcionId())
                            .moduloId(requestDTO.moduloId())
                            .build();

                    return seguimientoProgresoRepository.save(seguimiento)
                            .map(this::toSeguimientoProgresoResponseDTO);
                }));
    }

    @SuppressWarnings("unused")
    private Mono<SeguimientoProgresoResponseDTO> fallbackCreateSeguimientoProgreso(
            SeguimientoProgresoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createSeguimientoProgreso activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Seguimiento de Progreso no está disponible o falló al crear el registro."));
    }

    @Override
    @CircuitBreaker(name = SEGUIMIENTO_PROGRESO_SERVICE_CB, fallbackMethod = "fallbackGetSeguimientoProgresoById")
    public Mono<SeguimientoProgresoResponseDTO> getSeguimientoProgresoById(UUID id) {
        return seguimientoProgresoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Seguimiento de Progreso no encontrado con ID: " + id)))
                .map(this::toSeguimientoProgresoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<SeguimientoProgresoResponseDTO> fallbackGetSeguimientoProgresoById(UUID id, Throwable t) {
        log.error("Fallback para getSeguimientoProgresoById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Seguimiento de Progreso no está disponible o falló al obtener el registro."));
    }

    @Override
    @CircuitBreaker(name = SEGUIMIENTO_PROGRESO_SERVICE_CB, fallbackMethod = "fallbackGetAllSeguimientosProgreso")
    public Flux<SeguimientoProgresoResponseDTO> getAllSeguimientosProgreso() {
        return seguimientoProgresoRepository.findAll()
                .map(this::toSeguimientoProgresoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<SeguimientoProgresoResponseDTO> fallbackGetAllSeguimientosProgreso(Throwable t) {
        log.error("Fallback para getAllSeguimientosProgreso activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = SEGUIMIENTO_PROGRESO_SERVICE_CB, fallbackMethod = "fallbackUpdateSeguimientoProgreso")
    public Mono<SeguimientoProgresoResponseDTO> updateSeguimientoProgreso(UUID id,
            SeguimientoProgresoRequestDTO requestDTO) {

        return seguimientoProgresoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Seguimiento de Progreso no encontrado para actualizar con ID: " + id)))
                .flatMap(existingSeguimiento -> {
                    existingSeguimiento.setFechaUltimoAcceso(LocalDateTime.now()); 
                    existingSeguimiento.setCompletado(requestDTO.completado());
                    existingSeguimiento.setPuntajeObtenidoModulo(requestDTO.puntajeObtenidoModulo());
                    existingSeguimiento.setInscripcionId(requestDTO.inscripcionId());
                    existingSeguimiento.setModuloId(requestDTO.moduloId());

                    return seguimientoProgresoRepository.save(existingSeguimiento);
                })
                .map(this::toSeguimientoProgresoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<SeguimientoProgresoResponseDTO> fallbackUpdateSeguimientoProgreso(
            UUID id, SeguimientoProgresoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateSeguimientoProgreso activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Seguimiento de Progreso no está disponible o falló al actualizar el registro."));
    }

    @Override
    @CircuitBreaker(name = SEGUIMIENTO_PROGRESO_SERVICE_CB, fallbackMethod = "fallbackDeleteSeguimientoProgreso")
    public Mono<Void> deleteSeguimientoProgreso(UUID id) {
        return seguimientoProgresoRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteSeguimientoProgreso(UUID id, Throwable t) {
        log.error("Fallback para deleteSeguimientoProgreso activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Seguimiento de Progreso no está disponible o falló al eliminar el registro."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private SeguimientoProgresoResponseDTO toSeguimientoProgresoResponseDTO(SeguimientoProgreso seguimiento) {
        return new SeguimientoProgresoResponseDTO(
            seguimiento.getId(),
            seguimiento.getFechaUltimoAcceso(),
            seguimiento.getCompletado(),
            seguimiento.getPuntajeObtenidoModulo(),
            seguimiento.getInscripcionId(),
            seguimiento.getModuloId() 
        );
    }
}
