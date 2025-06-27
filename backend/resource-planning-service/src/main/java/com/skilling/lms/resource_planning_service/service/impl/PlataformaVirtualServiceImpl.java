package com.skilling.lms.resource_planning_service.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.resource_planning_service.domains.PlataformaVirtual;
import com.skilling.lms.resource_planning_service.repositories.PlataformaVirtualRepository;
import com.skilling.lms.resource_planning_service.service.PlataformaVirtualService;
import com.skilling.lms.shared.dtos.resources_planning.request.PlataformaVirtualRequestDTO;
import com.skilling.lms.shared.dtos.resources_planning.response.PlataformaVirtualResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlataformaVirtualServiceImpl implements PlataformaVirtualService {
    
    private final PlataformaVirtualRepository plataformaVirtualRepository;

    private static final String PLATAFORMA_VIRTUAL_SERVICE_CB = "plataformaVirtualServiceCB";

    @Override
    @CircuitBreaker(name = PLATAFORMA_VIRTUAL_SERVICE_CB, fallbackMethod = "fallbackCreatePlataformaVirtual")
    public Mono<PlataformaVirtualResponseDTO> createPlataformaVirtual(PlataformaVirtualRequestDTO requestDTO) {
        PlataformaVirtual plataformaVirtual = PlataformaVirtual.builder()
                .nombrePlataforma(requestDTO.nombrePlataforma())
                .url(requestDTO.url())
                .tipo(requestDTO.tipo())
                .credencialesApi(requestDTO.credencialesApi()) 
                .build();

        return plataformaVirtualRepository.save(plataformaVirtual)
                .map(this::toPlataformaVirtualResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PlataformaVirtualResponseDTO> fallbackCreatePlataformaVirtual(PlataformaVirtualRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createPlataformaVirtual activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Plataforma Virtual no está disponible o falló al crear la plataforma."));
    }

    @Override
    @CircuitBreaker(name = PLATAFORMA_VIRTUAL_SERVICE_CB, fallbackMethod = "fallbackGetPlataformaVirtualById")
    public Mono<PlataformaVirtualResponseDTO> getPlataformaVirtualById(UUID id) {
        return plataformaVirtualRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Plataforma Virtual no encontrada con ID: " + id)))
                .map(this::toPlataformaVirtualResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PlataformaVirtualResponseDTO> fallbackGetPlataformaVirtualById(UUID id, Throwable t) {
        log.error("Fallback para getPlataformaVirtualById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Plataforma Virtual no está disponible o falló al obtener la plataforma."));
    }

    @Override
    @CircuitBreaker(name = PLATAFORMA_VIRTUAL_SERVICE_CB, fallbackMethod = "fallbackGetAllPlataformasVirtuales")
    public Flux<PlataformaVirtualResponseDTO> getAllPlataformasVirtuales() {
        return plataformaVirtualRepository.findAll()
                .map(this::toPlataformaVirtualResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<PlataformaVirtualResponseDTO> fallbackGetAllPlataformasVirtuales(Throwable t) {
        log.error("Fallback para getAllPlataformasVirtuales activado. Causa: {}", t.getMessage());
        return Flux.empty(); 
    }

    @Override
    @CircuitBreaker(name = PLATAFORMA_VIRTUAL_SERVICE_CB, fallbackMethod = "fallbackUpdatePlataformaVirtual")
    public Mono<PlataformaVirtualResponseDTO> updatePlataformaVirtual(UUID id, PlataformaVirtualRequestDTO requestDTO) {
        return plataformaVirtualRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Plataforma Virtual no encontrada para actualizar con ID: " + id)))
                .flatMap(existingPlataformaVirtual -> {
                    existingPlataformaVirtual.setNombrePlataforma(requestDTO.nombrePlataforma());
                    existingPlataformaVirtual.setUrl(requestDTO.url());
                    existingPlataformaVirtual.setTipo(requestDTO.tipo());
                    existingPlataformaVirtual.setCredencialesApi(requestDTO.credencialesApi()); // Considerar seguridad aquí
                    
                    return plataformaVirtualRepository.save(existingPlataformaVirtual);
                })
                .map(this::toPlataformaVirtualResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PlataformaVirtualResponseDTO> fallbackUpdatePlataformaVirtual(UUID id, PlataformaVirtualRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updatePlataformaVirtual activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Plataforma Virtual no está disponible o falló al actualizar la plataforma."));
    }

    @Override
    @CircuitBreaker(name = PLATAFORMA_VIRTUAL_SERVICE_CB, fallbackMethod = "fallbackDeletePlataformaVirtual")
    public Mono<Void> deletePlataformaVirtual(UUID id) {
        return plataformaVirtualRepository.deleteById(id)
                .then(Mono.empty()); 
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeletePlataformaVirtual(UUID id, Throwable t) {
        log.error("Fallback para deletePlataformaVirtual activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Plataforma Virtual no está disponible o falló al eliminar la plataforma."));
    }

    // -- Método Auxiliar para Mapeo Simple
    private PlataformaVirtualResponseDTO toPlataformaVirtualResponseDTO(PlataformaVirtual plataformaVirtual) {
        return new PlataformaVirtualResponseDTO(
            plataformaVirtual.getId(),
            plataformaVirtual.getNombrePlataforma(),
            plataformaVirtual.getUrl(),
            plataformaVirtual.getTipo(),
            plataformaVirtual.getCredencialesApi()
        );
    }

}
