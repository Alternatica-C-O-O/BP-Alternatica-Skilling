package com.skilling.lms.enrollment_service.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.enrollment_service.domains.Asistencia;
import com.skilling.lms.enrollment_service.repositories.AsistenciaRepository;
import com.skilling.lms.enrollment_service.service.AsistenciaService;
import com.skilling.lms.shared.dtos.enrollment.request.AsistenciaRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.AsistenciaResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class AsistenciaServiceImpl implements AsistenciaService {
    
    private final AsistenciaRepository asistenciaRepository;

    private static final String ASISTENCIA_SERVICE_CB = "asistenciaServiceCB";

    @Override
    @CircuitBreaker(name = ASISTENCIA_SERVICE_CB, fallbackMethod = "fallbackCreateAsistencia")
    public Mono<AsistenciaResponseDTO> createAsistencia(AsistenciaRequestDTO requestDTO) {
        return asistenciaRepository.findByInscripcionIdAndFechaClase(requestDTO.inscripcionId(), LocalDate.now())
                .flatMap(existingAsistencia -> {
                    log.warn("Intento de crear asistencia duplicada para Inscripción ID: {} en la fecha: {}",
                            requestDTO.inscripcionId(), LocalDate.now());
                    return Mono.error(new RuntimeException("Ya existe un registro de asistencia para esta inscripción en la fecha actual."));
                })
                .then(Mono.defer(() -> {
                    Asistencia asistencia = Asistencia.builder()
                            .fechaClase(LocalDate.now()) 
                            .estadoAsistencia(requestDTO.estadoAsistencia())
                            .horaRegistro(LocalTime.now())
                            .inscripcionId(requestDTO.inscripcionId())
                            .build();

                    return asistenciaRepository.save(asistencia)
                            .map(this::toAsistenciaResponseDTO);
                }));
    }

    @SuppressWarnings("unused")
    private Mono<AsistenciaResponseDTO> fallbackCreateAsistencia(
            AsistenciaRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createAsistencia activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Asistencia no está disponible o falló al crear el registro de asistencia."));
    }

    @Override
    @CircuitBreaker(name = ASISTENCIA_SERVICE_CB, fallbackMethod = "fallbackGetAsistenciaById")
    public Mono<AsistenciaResponseDTO> getAsistenciaById(UUID id) {
        return asistenciaRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Registro de Asistencia no encontrado con ID: " + id)))
                .map(this::toAsistenciaResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<AsistenciaResponseDTO> fallbackGetAsistenciaById(UUID id, Throwable t) {
        log.error("Fallback para getAsistenciaById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Asistencia no está disponible o falló al obtener el registro."));
    }

    @Override
    @CircuitBreaker(name = ASISTENCIA_SERVICE_CB, fallbackMethod = "fallbackGetAllAsistencias")
    public Flux<AsistenciaResponseDTO> getAllAsistencias() {
        return asistenciaRepository.findAll()
                .map(this::toAsistenciaResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<AsistenciaResponseDTO> fallbackGetAllAsistencias(Throwable t) {
        log.error("Fallback para getAllAsistencias activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = ASISTENCIA_SERVICE_CB, fallbackMethod = "fallbackUpdateAsistencia")
    public Mono<AsistenciaResponseDTO> updateAsistencia(UUID id,
            AsistenciaRequestDTO requestDTO) {

        return asistenciaRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Registro de Asistencia no encontrado para actualizar con ID: " + id)))
                .flatMap(existingAsistencia -> {
                    existingAsistencia.setEstadoAsistencia(requestDTO.estadoAsistencia());
                    existingAsistencia.setInscripcionId(requestDTO.inscripcionId());

                    return asistenciaRepository.save(existingAsistencia);
                })
                .map(this::toAsistenciaResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<AsistenciaResponseDTO> fallbackUpdateAsistencia(
            UUID id, AsistenciaRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateAsistencia activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Asistencia no está disponible o falló al actualizar el registro."));
    }

    @Override
    @CircuitBreaker(name = ASISTENCIA_SERVICE_CB, fallbackMethod = "fallbackDeleteAsistencia")
    public Mono<Void> deleteAsistencia(UUID id) {
        return asistenciaRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteAsistencia(UUID id, Throwable t) {
        log.error("Fallback para deleteAsistencia activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Asistencia no está disponible o falló al eliminar el registro."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private AsistenciaResponseDTO toAsistenciaResponseDTO(Asistencia asistencia) {
        return new AsistenciaResponseDTO(
            asistencia.getId(),
            asistencia.getFechaClase(),
            asistencia.getEstadoAsistencia(), 
            asistencia.getHoraRegistro(),
            asistencia.getInscripcionId()
        );
    }
}
