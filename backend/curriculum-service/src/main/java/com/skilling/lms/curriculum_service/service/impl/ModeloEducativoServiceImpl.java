package com.skilling.lms.curriculum_service.service.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.curriculum_service.domains.ModeloEducativo;
import com.skilling.lms.curriculum_service.repositories.ModeloEducativoRepository;
import com.skilling.lms.curriculum_service.service.ModeloEducativoService;
import com.skilling.lms.shared.dtos.curriculum.request.ModeloEducativoRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.ModeloEducativoResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ModeloEducativoServiceImpl implements ModeloEducativoService {

    private final ModeloEducativoRepository modeloEducativoRepository;

    private static final String MODELO_EDUCATIVO_SERVICE_CB = "modeloEducativoServiceCB";

    @Override
    @CircuitBreaker(name = MODELO_EDUCATIVO_SERVICE_CB, fallbackMethod = "fallbackCreateModeloEducativo")
    public Mono<ModeloEducativoResponseDTO> createModeloEducativo(ModeloEducativoRequestDTO requestDTO) {
        return modeloEducativoRepository.findByNombreModeloAndVersion(requestDTO.nombreModelo(), requestDTO.version())
                .flatMap(existingModelo -> {
                    log.warn("Intento de crear modelo educativo duplicado: Nombre '{}', Versión '{}'",
                            requestDTO.nombreModelo(), requestDTO.version());
                    return Mono.error(new RuntimeException("Ya existe un modelo educativo con este nombre y versión."));
                })
                .then(Mono.defer(() -> {
                    ModeloEducativo modeloEducativo = ModeloEducativo.builder()
                            .nombreModelo(requestDTO.nombreModelo())
                            .descripcion(requestDTO.descripcion())
                            .version(requestDTO.version())
                            .fechaCreacion(LocalDate.now()) 
                            .estado(requestDTO.estado())
                            .usuariosId(requestDTO.usuariosId())
                            .build();

                    return modeloEducativoRepository.save(modeloEducativo)
                            .map(this::toModeloEducativoResponseDTO);
                }));
    }

    @SuppressWarnings("unused")
    private Mono<ModeloEducativoResponseDTO> fallbackCreateModeloEducativo(
            ModeloEducativoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createModeloEducativo activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Modelos Educativos no está disponible o falló al crear el modelo."));
    }

    @Override
    @CircuitBreaker(name = MODELO_EDUCATIVO_SERVICE_CB, fallbackMethod = "fallbackGetModeloEducativoById")
    public Mono<ModeloEducativoResponseDTO> getModeloEducativoById(UUID id) {
        return modeloEducativoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Modelo Educativo no encontrado con ID: " + id)))
                .map(this::toModeloEducativoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<ModeloEducativoResponseDTO> fallbackGetModeloEducativoById(UUID id, Throwable t) {
        log.error("Fallback para getModeloEducativoById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Modelos Educativos no está disponible o falló al obtener el modelo."));
    }

    @Override
    @CircuitBreaker(name = MODELO_EDUCATIVO_SERVICE_CB, fallbackMethod = "fallbackGetAllModelosEducativos")
    public Flux<ModeloEducativoResponseDTO> getAllModelosEducativos() {
        return modeloEducativoRepository.findAll()
                .map(this::toModeloEducativoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<ModeloEducativoResponseDTO> fallbackGetAllModelosEducativos(Throwable t) {
        log.error("Fallback para getAllModelosEducativos activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = MODELO_EDUCATIVO_SERVICE_CB, fallbackMethod = "fallbackUpdateModeloEducativo")
    public Mono<ModeloEducativoResponseDTO> updateModeloEducativo(UUID id, ModeloEducativoRequestDTO requestDTO) {
        return modeloEducativoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Modelo Educativo no encontrado para actualizar con ID: " + id)))
                .flatMap(existingModelo -> {
                    existingModelo.setNombreModelo(requestDTO.nombreModelo());
                    existingModelo.setDescripcion(requestDTO.descripcion());
                    existingModelo.setVersion(requestDTO.version());
                    existingModelo.setEstado(requestDTO.estado());
                    existingModelo.setUsuariosId(requestDTO.usuariosId());

                    return modeloEducativoRepository.save(existingModelo);
                })
                .map(this::toModeloEducativoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<ModeloEducativoResponseDTO> fallbackUpdateModeloEducativo(
            UUID id, ModeloEducativoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateModeloEducativo activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Modelos Educativos no está disponible o falló al actualizar el modelo."));
    }

    @Override
    @CircuitBreaker(name = MODELO_EDUCATIVO_SERVICE_CB, fallbackMethod = "fallbackDeleteModeloEducativo")
    public Mono<Void> deleteModeloEducativo(UUID id) {
        return modeloEducativoRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteModeloEducativo(UUID id, Throwable t) {
        log.error("Fallback para deleteModeloEducativo activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Modelos Educativos no está disponible o falló al eliminar el modelo."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private ModeloEducativoResponseDTO toModeloEducativoResponseDTO(ModeloEducativo modelo) {
        return new ModeloEducativoResponseDTO(
            modelo.getId(),
            modelo.getNombreModelo(),
            modelo.getDescripcion(),
            modelo.getVersion(),
            modelo.getFechaCreacion(),
            modelo.getEstado(),
            modelo.getUsuariosId()
        );
    }


}
