package com.skilling.lms.enrollment_service.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.enrollment_service.domains.PublicacionForo;
import com.skilling.lms.enrollment_service.repositories.PublicacionForoRepository;
import com.skilling.lms.enrollment_service.service.PublicacionForoService;
import com.skilling.lms.shared.dtos.enrollment.request.PublicacionForoRequestDTO;
import com.skilling.lms.shared.dtos.enrollment.response.PublicacionForoResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicacionForoServiceImpl implements PublicacionForoService {
    
    private final PublicacionForoRepository publicacionForoRepository;

    private static final String PUBLICACION_FORO_SERVICE_CB = "publicacionForoServiceCB";

    @Override
    @CircuitBreaker(name = PUBLICACION_FORO_SERVICE_CB, fallbackMethod = "fallbackCreatePublicacionForo")
    public Mono<PublicacionForoResponseDTO> createPublicacionForo(PublicacionForoRequestDTO requestDTO) {
        PublicacionForo publicacion = PublicacionForo.builder()
                .contenido(requestDTO.contenido())
                .fechaPublicacion(LocalDateTime.now()) 
                .usuariosId(requestDTO.usuariosId())
                .foroId(requestDTO.foroId())
                .publicacionForoId(requestDTO.publicacionForoId())
                .build();

        return publicacionForoRepository.save(publicacion)
                .map(this::toPublicacionForoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PublicacionForoResponseDTO> fallbackCreatePublicacionForo(
            PublicacionForoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createPublicacionForo activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Publicaciones de Foro no está disponible o falló al crear la publicación."));
    }

    @Override
    @CircuitBreaker(name = PUBLICACION_FORO_SERVICE_CB, fallbackMethod = "fallbackGetPublicacionForoById")
    public Mono<PublicacionForoResponseDTO> getPublicacionForoById(UUID id) {
        return publicacionForoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Publicación de Foro no encontrada con ID: " + id)))
                .map(this::toPublicacionForoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PublicacionForoResponseDTO> fallbackGetPublicacionForoById(UUID id, Throwable t) {
        log.error("Fallback para getPublicacionForoById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Publicaciones de Foro no está disponible o falló al obtener la publicación."));
    }

    @Override
    @CircuitBreaker(name = PUBLICACION_FORO_SERVICE_CB, fallbackMethod = "fallbackGetAllPublicacionesForo")
    public Flux<PublicacionForoResponseDTO> getAllPublicacionesForo() {
        return publicacionForoRepository.findAll()
                .map(this::toPublicacionForoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<PublicacionForoResponseDTO> fallbackGetAllPublicacionesForo(Throwable t) {
        log.error("Fallback para getAllPublicacionesForo activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = PUBLICACION_FORO_SERVICE_CB, fallbackMethod = "fallbackUpdatePublicacionForo")
    public Mono<PublicacionForoResponseDTO> updatePublicacionForo(UUID id, PublicacionForoRequestDTO requestDTO) {
        return publicacionForoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Publicación de Foro no encontrada para actualizar con ID: " + id)))
                .flatMap(existingPublicacion -> {
                    existingPublicacion.setContenido(requestDTO.contenido());

                    return publicacionForoRepository.save(existingPublicacion);
                })
                .map(this::toPublicacionForoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PublicacionForoResponseDTO> fallbackUpdatePublicacionForo(
            UUID id, PublicacionForoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updatePublicacionForo activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Publicaciones de Foro no está disponible o falló al actualizar la publicación."));
    }

    @Override
    @CircuitBreaker(name = PUBLICACION_FORO_SERVICE_CB, fallbackMethod = "fallbackDeletePublicacionForo")
    public Mono<Void> deletePublicacionForo(UUID id) {
        return publicacionForoRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeletePublicacionForo(UUID id, Throwable t) {
        log.error("Fallback para deletePublicacionForo activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Publicaciones de Foro no está disponible o falló al eliminar la publicación."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private PublicacionForoResponseDTO toPublicacionForoResponseDTO(PublicacionForo publicacionForo) {
        return new PublicacionForoResponseDTO(
            publicacionForo.getId(),
            publicacionForo.getContenido(),
            publicacionForo.getFechaPublicacion(),
            publicacionForo.getUsuariosId(),   
            publicacionForo.getForoId(),       
            publicacionForo.getPublicacionForoId() 
        );
    }
}
