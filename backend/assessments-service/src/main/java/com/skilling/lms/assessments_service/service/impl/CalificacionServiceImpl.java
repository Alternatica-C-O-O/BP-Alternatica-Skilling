package com.skilling.lms.assessments_service.service.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.assessments_service.domains.Calificacion;
import com.skilling.lms.assessments_service.repository.CalificacionRepository;
import com.skilling.lms.assessments_service.service.CalificacionService;
import com.skilling.lms.shared.dtos.assessments.request.CalificacionRequestDTO;
import com.skilling.lms.shared.dtos.assessments.response.CalificacionResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalificacionServiceImpl implements CalificacionService {
    
    private final CalificacionRepository calificacionRepository;

    private static final String CALIFICACION_SERVICE_CB = "calificacionServiceCB";

    @Override
    @CircuitBreaker(name = CALIFICACION_SERVICE_CB, fallbackMethod = "fallbackCreateCalificacion")
    public Mono<CalificacionResponseDTO> createCalificacion(CalificacionRequestDTO requestDTO) {
        return calificacionRepository.findByInscripcionIdAndEvaluacionId(requestDTO.inscripcionId(), requestDTO.evaluacionId())
                .flatMap(existingCalificacion -> {
                    log.warn("Intento de crear calificación duplicada para Inscripción ID: {} y Evaluación ID: {}",
                            requestDTO.inscripcionId(), requestDTO.evaluacionId());
                    return Mono.error(new RuntimeException("Ya existe una calificación para esta inscripción y evaluación."));
                })
                .then(Mono.defer(() -> {
                    Calificacion calificacion = Calificacion.builder()
                            .puntajeObtenido(requestDTO.puntajeObtenido())
                            .fechaCalificacion(LocalDate.now()) // Fecha de calificación al momento de la creación
                            .comentariosDocente(requestDTO.comentariosDocente())
                            .inscripcionId(requestDTO.inscripcionId())
                            .evaluacionId(requestDTO.evaluacionId())
                            .build();

                    return calificacionRepository.save(calificacion)
                            .map(this::toCalificacionResponseDTO);
                }));
    }

    @SuppressWarnings("unused")
    private Mono<CalificacionResponseDTO> fallbackCreateCalificacion(
            CalificacionRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createCalificacion activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Calificaciones no está disponible o falló al crear la calificación."));
    }

    @Override
    @CircuitBreaker(name = CALIFICACION_SERVICE_CB, fallbackMethod = "fallbackGetCalificacionById")
    public Mono<CalificacionResponseDTO> getCalificacionById(UUID id) {
        return calificacionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Calificación no encontrada con ID: " + id)))
                .map(this::toCalificacionResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<CalificacionResponseDTO> fallbackGetCalificacionById(UUID id, Throwable t) {
        log.error("Fallback para getCalificacionById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Calificaciones no está disponible o falló al obtener la calificación."));
    }

    @Override
    @CircuitBreaker(name = CALIFICACION_SERVICE_CB, fallbackMethod = "fallbackGetAllCalificaciones")
    public Flux<CalificacionResponseDTO> getAllCalificaciones() {
        return calificacionRepository.findAll()
                .map(this::toCalificacionResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<CalificacionResponseDTO> fallbackGetAllCalificaciones(Throwable t) {
        log.error("Fallback para getAllCalificaciones activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = CALIFICACION_SERVICE_CB, fallbackMethod = "fallbackUpdateCalificacion")
    public Mono<CalificacionResponseDTO> updateCalificacion(UUID id,
            CalificacionRequestDTO requestDTO) {

        return calificacionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Calificación no encontrada para actualizar con ID: " + id)))
                .flatMap(existingCalificacion -> {
                    existingCalificacion.setPuntajeObtenido(requestDTO.puntajeObtenido());
                    existingCalificacion.setComentariosDocente(requestDTO.comentariosDocente());
                    existingCalificacion.setInscripcionId(requestDTO.inscripcionId());
                    existingCalificacion.setEvaluacionId(requestDTO.evaluacionId());

                    return calificacionRepository.save(existingCalificacion);
                })
                .map(this::toCalificacionResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<CalificacionResponseDTO> fallbackUpdateCalificacion(
            UUID id, CalificacionRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateCalificacion activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Calificaciones no está disponible o falló al actualizar la calificación."));
    }

    @Override
    @CircuitBreaker(name = CALIFICACION_SERVICE_CB, fallbackMethod = "fallbackDeleteCalificacion")
    public Mono<Void> deleteCalificacion(UUID id) {
        return calificacionRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteCalificacion(UUID id, Throwable t) {
        log.error("Fallback para deleteCalificacion activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Calificaciones no está disponible o falló al eliminar la calificación."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private CalificacionResponseDTO toCalificacionResponseDTO(Calificacion calificacion) {
        return new CalificacionResponseDTO(
            calificacion.getId(),
            calificacion.getPuntajeObtenido(),
            calificacion.getFechaCalificacion(),
            calificacion.getComentariosDocente(),
            calificacion.getInscripcionId(),
            calificacion.getEvaluacionId() 
        );
    }
}
