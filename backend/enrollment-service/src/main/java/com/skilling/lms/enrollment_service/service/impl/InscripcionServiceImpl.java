package com.skilling.lms.enrollment_service.service.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.enrollment_service.domains.Inscripcion;
import com.skilling.lms.enrollment_service.repositories.InscripcionRepository;
import com.skilling.lms.enrollment_service.service.InscripcionService;
import com.skilling.lms.shared.dtos.enrollment.request.InscripcionRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.InscripcionResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class InscripcionServiceImpl implements InscripcionService {
    
    private final InscripcionRepository inscripcionRepository;

    private static final String INSCRIPCION_SERVICE_CB = "inscripcionServiceCB";

    @Override
    @CircuitBreaker(name = INSCRIPCION_SERVICE_CB, fallbackMethod = "fallbackCreateInscripcion")
    public Mono<InscripcionResponseDTO> createInscripcion(InscripcionRequestDTO requestDTO) {
        return inscripcionRepository.findByUsuariosIdAndCursoOfertadoId(requestDTO.usuariosId(), requestDTO.cursoOfertadoId())
                .flatMap(existingInscripcion -> {
                    log.warn("Intento de crear inscripción duplicada para Usuario ID: {} y Curso Ofertado ID: {}",
                            requestDTO.usuariosId(), requestDTO.cursoOfertadoId());
                    return Mono.error(new RuntimeException("Ya existe una inscripción para este usuario en el curso ofertado."));
                })
                .then(Mono.defer(() -> {
                    Inscripcion inscripcion = Inscripcion.builder()
                            .fechaInscripcion(LocalDate.now()) 
                            .estado(requestDTO.estado())
                            .fechaFinalizacion(requestDTO.fechaFinalizacion())
                            .progresoPorcentaje(requestDTO.progresoPorcentaje())
                            .usuariosId(requestDTO.usuariosId())
                            .cursoOfertadoId(requestDTO.cursoOfertadoId())
                            .build();

                    return inscripcionRepository.save(inscripcion)
                            .map(this::toInscripcionResponseDTO);
                }));
    }

    @SuppressWarnings("unused")
    private Mono<InscripcionResponseDTO> fallbackCreateInscripcion(
            InscripcionRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createInscripcion activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Inscripciones no está disponible o falló al crear la inscripción."));
    }

    @Override
    @CircuitBreaker(name = INSCRIPCION_SERVICE_CB, fallbackMethod = "fallbackGetInscripcionById")
    public Mono<InscripcionResponseDTO> getInscripcionById(UUID id) {
        return inscripcionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Inscripción no encontrada con ID: " + id)))
                .map(this::toInscripcionResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<InscripcionResponseDTO> fallbackGetInscripcionById(UUID id, Throwable t) {
        log.error("Fallback para getInscripcionById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Inscripciones no está disponible o falló al obtener la inscripción."));
    }

    @Override
    @CircuitBreaker(name = INSCRIPCION_SERVICE_CB, fallbackMethod = "fallbackGetAllInscripciones")
    public Flux<InscripcionResponseDTO> getAllInscripciones() {
        return inscripcionRepository.findAll()
                .map(this::toInscripcionResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<InscripcionResponseDTO> fallbackGetAllInscripciones(Throwable t) {
        log.error("Fallback para getAllInscripciones activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = INSCRIPCION_SERVICE_CB, fallbackMethod = "fallbackUpdateInscripcion")
    public Mono<InscripcionResponseDTO> updateInscripcion(UUID id,
            InscripcionRequestDTO requestDTO) {

        return inscripcionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Inscripción no encontrada para actualizar con ID: " + id)))
                .flatMap(existingInscripcion -> {
                    existingInscripcion.setEstado(requestDTO.estado());
                    existingInscripcion.setFechaFinalizacion(requestDTO.fechaFinalizacion());
                    existingInscripcion.setProgresoPorcentaje(requestDTO.progresoPorcentaje());
                    existingInscripcion.setUsuariosId(requestDTO.usuariosId());
                    existingInscripcion.setCursoOfertadoId(requestDTO.cursoOfertadoId());

                    return inscripcionRepository.save(existingInscripcion);
                })
                .map(this::toInscripcionResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<InscripcionResponseDTO> fallbackUpdateInscripcion(
            UUID id, InscripcionRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateInscripcion activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Inscripciones no está disponible o falló al actualizar la inscripción."));
    }

    @Override
    @CircuitBreaker(name = INSCRIPCION_SERVICE_CB, fallbackMethod = "fallbackDeleteInscripcion")
    public Mono<Void> deleteInscripcion(UUID id) {
        return inscripcionRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteInscripcion(UUID id, Throwable t) {
        log.error("Fallback para deleteInscripcion activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Inscripciones no está disponible o falló al eliminar la inscripción."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private InscripcionResponseDTO toInscripcionResponseDTO(Inscripcion inscripcion) {
        return new InscripcionResponseDTO(
            inscripcion.getId(),
            inscripcion.getFechaInscripcion(),
            inscripcion.getEstado(),
            inscripcion.getFechaFinalizacion(),
            inscripcion.getProgresoPorcentaje(),
            inscripcion.getUsuariosId(), 
            inscripcion.getCursoOfertadoId()
        );
    }
}
