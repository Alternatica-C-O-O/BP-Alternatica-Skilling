package com.skilling.lms.enrollment_service.service.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.enrollment_service.domains.Foro;
import com.skilling.lms.enrollment_service.repositories.ForoRepository;
import com.skilling.lms.enrollment_service.service.ForoService;
import com.skilling.lms.shared.dtos.enrollment.request.ForoRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.ForoResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ForoServiceImpl implements ForoService {
    
    private final ForoRepository foroRepository;

    private static final String FORO_SERVICE_CB = "foroServiceCB";

    @Override
    @CircuitBreaker(name = FORO_SERVICE_CB, fallbackMethod = "fallbackCreateForo")
    public Mono<ForoResponseDTO> createForo(ForoRequestDTO requestDTO) {
        return foroRepository.findByNombreForoAndCursoOfertadoId(requestDTO.nombreForo(), requestDTO.cursoOfertadoId())
                .flatMap(existingForo -> {
                    log.warn("Intento de crear foro duplicado: Nombre '{}' en Curso ID '{}'",
                            requestDTO.nombreForo(), requestDTO.cursoOfertadoId());
                    return Mono.error(new RuntimeException("Ya existe un foro con este nombre para el curso ofertado."));
                })
                .then(Mono.defer(() -> {
                    Foro foro = Foro.builder()
                            .nombreForo(requestDTO.nombreForo())
                            .descripcion(requestDTO.descripcion())
                            .fechaCreacion(LocalDate.now()) 
                            .cursoOfertadoId(requestDTO.cursoOfertadoId())
                            .build();

                    return foroRepository.save(foro)
                            .map(this::toForoResponseDTO);
                }));
    }

    @SuppressWarnings("unused")
    private Mono<ForoResponseDTO> fallbackCreateForo(
            ForoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createForo activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Foros no está disponible o falló al crear el foro."));
    }

    @Override
    @CircuitBreaker(name = FORO_SERVICE_CB, fallbackMethod = "fallbackGetForoById")
    public Mono<ForoResponseDTO> getForoById(UUID id) {
        return foroRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Foro no encontrado con ID: " + id)))
                .map(this::toForoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<ForoResponseDTO> fallbackGetForoById(UUID id, Throwable t) {
        log.error("Fallback para getForoById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Foros no está disponible o falló al obtener el foro."));
    }

    @Override
    @CircuitBreaker(name = FORO_SERVICE_CB, fallbackMethod = "fallbackGetAllForos")
    public Flux<ForoResponseDTO> getAllForos() {
        return foroRepository.findAll()
                .map(this::toForoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<ForoResponseDTO> fallbackGetAllForos(Throwable t) {
        log.error("Fallback para getAllForos activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = FORO_SERVICE_CB, fallbackMethod = "fallbackUpdateForo")
    public Mono<ForoResponseDTO> updateForo(UUID id,
            ForoRequestDTO requestDTO) {

        return foroRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Foro no encontrado para actualizar con ID: " + id)))
                .flatMap(existingForo -> {
                    existingForo.setNombreForo(requestDTO.nombreForo());
                    existingForo.setDescripcion(requestDTO.descripcion());
                    existingForo.setCursoOfertadoId(requestDTO.cursoOfertadoId());

                    return foroRepository.save(existingForo);
                })
                .map(this::toForoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<ForoResponseDTO> fallbackUpdateForo(
            UUID id, ForoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateForo activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Foros no está disponible o falló al actualizar el foro."));
    }

    @Override
    @CircuitBreaker(name = FORO_SERVICE_CB, fallbackMethod = "fallbackDeleteForo")
    public Mono<Void> deleteForo(UUID id) {
        return foroRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteForo(UUID id, Throwable t) {
        log.error("Fallback para deleteForo activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Foros no está disponible o falló al eliminar el foro."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private ForoResponseDTO toForoResponseDTO(Foro foro) {
        return new ForoResponseDTO(
            foro.getId(),
            foro.getNombreForo(),
            foro.getDescripcion(),
            foro.getFechaCreacion(),
            foro.getCursoOfertadoId()
        );
    }
}
