package com.skilling.lms.assessments_service.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.assessments_service.domains.Evaluacion;
import com.skilling.lms.assessments_service.repository.EvaluacionRepository;
import com.skilling.lms.assessments_service.service.EvaluacionService;
import com.skilling.lms.shared.dtos.assessments.request.EvaluacionRequestDTO;
import com.skilling.lms.shared.dtos.assessments.response.EvaluacionResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class EvaluacionServiceImpl implements EvaluacionService {
    
    private final EvaluacionRepository evaluacionRepository;

    private static final String EVALUACION_SERVICE_CB = "evaluacionServiceCB";

    @Override
    @CircuitBreaker(name = EVALUACION_SERVICE_CB, fallbackMethod = "fallbackCreateEvaluacion")
    public Mono<EvaluacionResponseDTO> createEvaluacion(EvaluacionRequestDTO requestDTO) {
        return evaluacionRepository.findByNombreEvaluacionAndCursoOfertadoId(requestDTO.nombreEvaluacion(), requestDTO.cursoOfertadoId())
                .flatMap(existingEvaluacion -> {
                    log.warn("Intento de crear evaluación duplicada: Nombre '{}' en Curso ID '{}'",
                            requestDTO.nombreEvaluacion(), requestDTO.cursoOfertadoId());
                    return Mono.error(new RuntimeException("Ya existe una evaluación con este nombre para el curso ofertado."));
                })
                .then(Mono.defer(() -> {
                    Evaluacion evaluacion = Evaluacion.builder()
                            .nombreEvaluacion(requestDTO.nombreEvaluacion())
                            .tipoEvaluacion(requestDTO.tipoEvaluacion())
                            .porcentajeCalificacion(requestDTO.porcentajeCalificacion())
                            .fechaVencimiento(requestDTO.fechaVencimiento())
                            .descripcion(requestDTO.descripcion())
                            .pesoRelativo(requestDTO.pesoRelativo())
                            .puntajeMaximo(requestDTO.puntajeMaximo())
                            .cursoOfertadoId(requestDTO.cursoOfertadoId())
                            .moduloId(requestDTO.moduloId())
                            .build();

                    return evaluacionRepository.save(evaluacion)
                            .map(this::toEvaluacionResponseDTO);
                }));
    }

    @SuppressWarnings("unused")
    private Mono<EvaluacionResponseDTO> fallbackCreateEvaluacion(
            EvaluacionRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createEvaluacion activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Evaluaciones no está disponible o falló al crear la evaluación."));
    }

    @Override
    @CircuitBreaker(name = EVALUACION_SERVICE_CB, fallbackMethod = "fallbackGetEvaluacionById")
    public Mono<EvaluacionResponseDTO> getEvaluacionById(UUID id) {
        return evaluacionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Evaluación no encontrada con ID: " + id)))
                .map(this::toEvaluacionResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<EvaluacionResponseDTO> fallbackGetEvaluacionById(UUID id, Throwable t) {
        log.error("Fallback para getEvaluacionById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Evaluaciones no está disponible o falló al obtener la evaluación."));
    }

    @Override
    @CircuitBreaker(name = EVALUACION_SERVICE_CB, fallbackMethod = "fallbackGetAllEvaluaciones")
    public Flux<EvaluacionResponseDTO> getAllEvaluaciones() {
        return evaluacionRepository.findAll()
                .map(this::toEvaluacionResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<EvaluacionResponseDTO> fallbackGetAllEvaluaciones(Throwable t) {
        log.error("Fallback para getAllEvaluaciones activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = EVALUACION_SERVICE_CB, fallbackMethod = "fallbackUpdateEvaluacion")
    public Mono<EvaluacionResponseDTO> updateEvaluacion(UUID id,
            EvaluacionRequestDTO requestDTO) {

        return evaluacionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Evaluación no encontrada para actualizar con ID: " + id)))
                .flatMap(existingEvaluacion -> {
                    existingEvaluacion.setNombreEvaluacion(requestDTO.nombreEvaluacion());
                    existingEvaluacion.setTipoEvaluacion(requestDTO.tipoEvaluacion());
                    existingEvaluacion.setPorcentajeCalificacion(requestDTO.porcentajeCalificacion());
                    existingEvaluacion.setFechaVencimiento(requestDTO.fechaVencimiento());
                    existingEvaluacion.setDescripcion(requestDTO.descripcion());
                    existingEvaluacion.setPesoRelativo(requestDTO.pesoRelativo());
                    existingEvaluacion.setPuntajeMaximo(requestDTO.puntajeMaximo());
                    existingEvaluacion.setCursoOfertadoId(requestDTO.cursoOfertadoId());
                    existingEvaluacion.setModuloId(requestDTO.moduloId());

                    return evaluacionRepository.save(existingEvaluacion);
                })
                .map(this::toEvaluacionResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<EvaluacionResponseDTO> fallbackUpdateEvaluacion(
            UUID id, EvaluacionRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateEvaluacion activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Evaluaciones no está disponible o falló al actualizar la evaluación."));
    }

    @Override
    @CircuitBreaker(name = EVALUACION_SERVICE_CB, fallbackMethod = "fallbackDeleteEvaluacion")
    public Mono<Void> deleteEvaluacion(UUID id) {
        return evaluacionRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteEvaluacion(UUID id, Throwable t) {
        log.error("Fallback para deleteEvaluacion activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Evaluaciones no está disponible o falló al eliminar la evaluación."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private EvaluacionResponseDTO toEvaluacionResponseDTO(Evaluacion evaluacion) {
        return new EvaluacionResponseDTO(
            evaluacion.getId(),
            evaluacion.getNombreEvaluacion(),
            evaluacion.getTipoEvaluacion(),
            evaluacion.getPorcentajeCalificacion(),
            evaluacion.getFechaVencimiento(),
            evaluacion.getDescripcion(),
            evaluacion.getPesoRelativo(),
            evaluacion.getPuntajeMaximo(),
            evaluacion.getCursoOfertadoId(), 
            evaluacion.getModuloId()
        );
    }
}
