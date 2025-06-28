package com.skilling.lms.curriculum_service.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.curriculum_service.domains.Competencia;
import com.skilling.lms.curriculum_service.repositories.CompetenciaRepository;
import com.skilling.lms.curriculum_service.service.CompetenciaService;
import com.skilling.lms.shared.dtos.curriculum.request.CompetenciaRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.CompetenciaResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompetenciaServiceImpl implements CompetenciaService {

    private final CompetenciaRepository competenciaRepository;

    private static final String COMPETENCIA_SERVICE_CB = "competenciaServiceCB";

    @Override
    @CircuitBreaker(name = COMPETENCIA_SERVICE_CB, fallbackMethod = "fallbackCreateCompetencia")
    public Mono<CompetenciaResponseDTO> createCompetencia(CompetenciaRequestDTO requestDTO) {
        return competenciaRepository.findByNombreCompetencia(requestDTO.nombreCompetencia())
                .flatMap(existingCompetencia -> {
                    log.warn("Intento de crear competencia duplicada: Nombre '{}'", requestDTO.nombreCompetencia());
                    return Mono.error(new RuntimeException("Ya existe una competencia con este nombre."));
                })
                .then(Mono.defer(() -> {
                    Competencia competencia = Competencia.builder()
                            .nombreCompetencia(requestDTO.nombreCompetencia())
                            .descripcion(requestDTO.descripcion())
                            .nivelEsperado(requestDTO.nivelEsperado())
                            .build();

                    return competenciaRepository.save(competencia)
                            .map(this::toCompetenciaResponseDTO);
                }));
    }

    @SuppressWarnings("unused")
    private Mono<CompetenciaResponseDTO> fallbackCreateCompetencia(
            CompetenciaRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createCompetencia activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Competencias no está disponible o falló al crear la competencia."));
    }

    @Override
    @CircuitBreaker(name = COMPETENCIA_SERVICE_CB, fallbackMethod = "fallbackGetCompetenciaById")
    public Mono<CompetenciaResponseDTO> getCompetenciaById(UUID id) {
        return competenciaRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Competencia no encontrada con ID: " + id)))
                .map(this::toCompetenciaResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<CompetenciaResponseDTO> fallbackGetCompetenciaById(UUID id, Throwable t) {
        log.error("Fallback para getCompetenciaById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Competencias no está disponible o falló al obtener la competencia."));
    }

    @Override
    @CircuitBreaker(name = COMPETENCIA_SERVICE_CB, fallbackMethod = "fallbackGetAllCompetencias")
    public Flux<CompetenciaResponseDTO> getAllCompetencias() {
        return competenciaRepository.findAll()
                .map(this::toCompetenciaResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<CompetenciaResponseDTO> fallbackGetAllCompetencias(Throwable t) {
        log.error("Fallback para getAllCompetencias activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = COMPETENCIA_SERVICE_CB, fallbackMethod = "fallbackUpdateCompetencia")
    public Mono<CompetenciaResponseDTO> updateCompetencia(UUID id, CompetenciaRequestDTO requestDTO) {
        return competenciaRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Competencia no encontrada para actualizar con ID: " + id)))
                .flatMap(existingCompetencia -> {
                    existingCompetencia.setNombreCompetencia(requestDTO.nombreCompetencia());
                    existingCompetencia.setDescripcion(requestDTO.descripcion());
                    existingCompetencia.setNivelEsperado(requestDTO.nivelEsperado());

                    return competenciaRepository.save(existingCompetencia);
                })
                .map(this::toCompetenciaResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<CompetenciaResponseDTO> fallbackUpdateCompetencia(
            UUID id, CompetenciaRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateCompetencia activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Competencias no está disponible o falló al actualizar la competencia."));
    }

    @Override
    @CircuitBreaker(name = COMPETENCIA_SERVICE_CB, fallbackMethod = "fallbackDeleteCompetencia")
    public Mono<Void> deleteCompetencia(UUID id) {
        return competenciaRepository.deleteById(id)
                .then(Mono.empty()); 
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteCompetencia(UUID id, Throwable t) {
        log.error("Fallback para deleteCompetencia activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Competencias no está disponible o falló al eliminar la competencia."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private CompetenciaResponseDTO toCompetenciaResponseDTO(Competencia competencia) {
        return new CompetenciaResponseDTO(
            competencia.getId(),
            competencia.getNombreCompetencia(),
            competencia.getDescripcion(),
            competencia.getNivelEsperado()
        );
    }

}
