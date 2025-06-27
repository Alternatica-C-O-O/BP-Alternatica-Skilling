package com.skilling.lms.content_service.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.content_service.domains.ContenidoModulo;
import com.skilling.lms.content_service.repositories.ContenidoModuloRepository;
import com.skilling.lms.content_service.service.ContenidoModuloService;
import com.skilling.lms.shared.dtos.content.request.ContenidoModuloRequestDTO;
import com.skilling.lms.shared.dtos.content.response.ContenidoModuloResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContenidoModuloServiceImpl implements ContenidoModuloService {
    
    private final ContenidoModuloRepository contenidoModuloRepository;

    private static final String CONTENIDO_MODULO_SERVICE_CB = "contenidoModuloServiceCB";

    @Override
    @CircuitBreaker(name = CONTENIDO_MODULO_SERVICE_CB, fallbackMethod = "fallbackCreateContenidoModulo")
    public Mono<ContenidoModuloResponseDTO> createContenidoModulo(ContenidoModuloRequestDTO requestDTO) {
        return contenidoModuloRepository.findByModuloIdAndRecursoDidacticoId(requestDTO.moduloId(), requestDTO.recursoDidacticoId())
            .flatMap(existing -> {
                log.warn("Intento de crear contenido de módulo duplicado: Módulo ID {} y Recurso Didáctico ID {}", requestDTO.moduloId(), requestDTO.recursoDidacticoId());
                return Mono.error(new RuntimeException("El recurso didáctico ya está asociado a este módulo."));
            })
            .then(Mono.defer(() -> {
                ContenidoModulo contenidoModulo = ContenidoModulo.builder()
                    .ordenModulo(requestDTO.ordenModulo())
                    .recursoDidacticoId(requestDTO.recursoDidacticoId())
                    .moduloId(requestDTO.moduloId())
                    .build();

                return contenidoModuloRepository.save(contenidoModulo)
                    .map(this::toContenidoModuloResponseDTO);
            }));
    }

    @SuppressWarnings("unused")
    private Mono<ContenidoModuloResponseDTO> fallbackCreateContenidoModulo(
            ContenidoModuloRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createContenidoModulo activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Contenido de Módulo no está disponible o falló al crear el contenido."));
    }

    @Override
    @CircuitBreaker(name = CONTENIDO_MODULO_SERVICE_CB, fallbackMethod = "fallbackGetContenidoModuloById")
    public Mono<ContenidoModuloResponseDTO> getContenidoModuloById(UUID id) {
        return contenidoModuloRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Contenido de Módulo no encontrado con ID: " + id)))
                .map(this::toContenidoModuloResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<ContenidoModuloResponseDTO> fallbackGetContenidoModuloById(UUID id, Throwable t) {
        log.error("Fallback para getContenidoModuloById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Contenido de Módulo no está disponible o falló al obtener el contenido."));
    }

    @Override
    @CircuitBreaker(name = CONTENIDO_MODULO_SERVICE_CB, fallbackMethod = "fallbackGetAllContenidosModulo")
    public Flux<ContenidoModuloResponseDTO> getAllContenidosModulo() {
        return contenidoModuloRepository.findAll()
                .map(this::toContenidoModuloResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<ContenidoModuloResponseDTO> fallbackGetAllContenidosModulo(Throwable t) {
        log.error("Fallback para getAllContenidosModulo activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = CONTENIDO_MODULO_SERVICE_CB, fallbackMethod = "fallbackUpdateContenidoModulo")
    public Mono<ContenidoModuloResponseDTO> updateContenidoModulo(UUID id,
            ContenidoModuloRequestDTO requestDTO) {

        return contenidoModuloRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Contenido de Módulo no encontrado para actualizar con ID: " + id)))
                .flatMap(existingContenidoModulo -> {
                    existingContenidoModulo.setOrdenModulo(requestDTO.ordenModulo());
                    existingContenidoModulo.setRecursoDidacticoId(requestDTO.recursoDidacticoId());
                    existingContenidoModulo.setModuloId(requestDTO.moduloId());
                    
                    return contenidoModuloRepository.save(existingContenidoModulo);
                })
                .map(this::toContenidoModuloResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<ContenidoModuloResponseDTO> fallbackUpdateContenidoModulo(
            UUID id, ContenidoModuloRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateContenidoModulo activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Contenido de Módulo no está disponible o falló al actualizar el contenido."));
    }

    @Override
    @CircuitBreaker(name = CONTENIDO_MODULO_SERVICE_CB, fallbackMethod = "fallbackDeleteContenidoModulo")
    public Mono<Void> deleteContenidoModulo(UUID id) {
        return contenidoModuloRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteContenidoModulo(UUID id, Throwable t) {
        log.error("Fallback para deleteContenidoModulo activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Contenido de Módulo no está disponible o falló al eliminar el contenido."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private ContenidoModuloResponseDTO toContenidoModuloResponseDTO(ContenidoModulo contenidoModulo) {
        return new ContenidoModuloResponseDTO(
            contenidoModulo.getId(),
            contenidoModulo.getOrdenModulo(),
            contenidoModulo.getRecursoDidacticoId(),
            contenidoModulo.getModuloId()  
        );
    }
}
