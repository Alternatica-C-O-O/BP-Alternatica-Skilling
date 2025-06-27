package com.skilling.lms.resource_planning_service.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.resource_planning_service.domains.EspacioFisico;
import com.skilling.lms.resource_planning_service.repositories.EspacioFisicoRepository;
import com.skilling.lms.resource_planning_service.service.EspacioFisicoService;
import com.skilling.lms.shared.dtos.resources_planning.request.EspacioFisicoRequestDTO;
import com.skilling.lms.shared.dtos.resources_planning.response.EspacioFisicoResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class EspacioFisicoServiceImpl implements EspacioFisicoService {
    
    private final EspacioFisicoRepository espacioFisicoRepository;

    private static final String ESPACIO_FISICO_SERVICE_CB = "espacioFisicoServiceCB";

    @Override
    @CircuitBreaker(name = ESPACIO_FISICO_SERVICE_CB, fallbackMethod = "fallbackCreateEspacioFisico")
    public Mono<EspacioFisicoResponseDTO> createEspacioFisico(EspacioFisicoRequestDTO requestDTO) {
        EspacioFisico espacioFisico = EspacioFisico.builder()
                .nombre(requestDTO.nombre())
                .capacidad(requestDTO.capacidad())
                .tipoEspacio(requestDTO.tipoEspacio()) 
                .ubicacion(requestDTO.ubicacion())
                .build();

        return espacioFisicoRepository.save(espacioFisico)
                .map(this::toEspacioFisicoResponseDTO);
    }

    @SuppressWarnings("unused") 
    private Mono<EspacioFisicoResponseDTO> fallbackCreateEspacioFisico(EspacioFisicoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createEspacioFisico activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Espacio Físico no está disponible o falló al crear el espacio."));
    }

    @Override
    @CircuitBreaker(name = ESPACIO_FISICO_SERVICE_CB, fallbackMethod = "fallbackGetEspacioFisicoById")
    public Mono<EspacioFisicoResponseDTO> getEspacioFisicoById(UUID id) {
        return espacioFisicoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Espacio Físico no encontrado con ID: " + id)))
                .map(this::toEspacioFisicoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<EspacioFisicoResponseDTO> fallbackGetEspacioFisicoById(UUID id, Throwable t) {
        log.error("Fallback para getEspacioFisicoById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Espacio Físico no está disponible o falló al obtener el espacio."));
    }

    @Override
    @CircuitBreaker(name = ESPACIO_FISICO_SERVICE_CB, fallbackMethod = "fallbackGetAllEspaciosFisicos")
    public Flux<EspacioFisicoResponseDTO> getAllEspaciosFisicos() {
        return espacioFisicoRepository.findAll()
                .map(this::toEspacioFisicoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<EspacioFisicoResponseDTO> fallbackGetAllEspaciosFisicos(Throwable t) {
        log.error("Fallback para getAllEspaciosFisicos activado. Causa: {}", t.getMessage());
        return Flux.empty(); 
    }

    @Override
    @CircuitBreaker(name = ESPACIO_FISICO_SERVICE_CB, fallbackMethod = "fallbackUpdateEspacioFisico")
    public Mono<EspacioFisicoResponseDTO> updateEspacioFisico(UUID id, EspacioFisicoRequestDTO requestDTO) {
        return espacioFisicoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Espacio Físico no encontrado para actualizar con ID: " + id)))
                .flatMap(existingEspacioFisico -> {
                    existingEspacioFisico.setNombre(requestDTO.nombre());
                    existingEspacioFisico.setCapacidad(requestDTO.capacidad());
                    existingEspacioFisico.setTipoEspacio(requestDTO.tipoEspacio());
                    existingEspacioFisico.setUbicacion(requestDTO.ubicacion());
                    
                    return espacioFisicoRepository.save(existingEspacioFisico);
                })
                .map(this::toEspacioFisicoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<EspacioFisicoResponseDTO> fallbackUpdateEspacioFisico(UUID id, EspacioFisicoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateEspacioFisico activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Espacio Físico no está disponible o falló al actualizar el espacio."));
    }

    @Override
    @CircuitBreaker(name = ESPACIO_FISICO_SERVICE_CB, fallbackMethod = "fallbackDeleteEspacioFisico")
    public Mono<Void> deleteEspacioFisico(UUID id) {
        return espacioFisicoRepository.deleteById(id)
                .then(Mono.empty()); 
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteEspacioFisico(UUID id, Throwable t) {
        log.error("Fallback para deleteEspacioFisico activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Espacio Físico no está disponible o falló al eliminar el espacio."));
    }

    // -- Método Auxiliar para Mapeo Simple
    private EspacioFisicoResponseDTO toEspacioFisicoResponseDTO(EspacioFisico espacioFisico) {
        return new EspacioFisicoResponseDTO(
            espacioFisico.getId(),
            espacioFisico.getNombre(),
            espacioFisico.getCapacidad(),
            espacioFisico.getTipoEspacio(),
            espacioFisico.getUbicacion()
        );
    }
}
