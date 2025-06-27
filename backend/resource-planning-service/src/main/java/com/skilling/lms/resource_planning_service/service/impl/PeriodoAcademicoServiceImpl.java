package com.skilling.lms.resource_planning_service.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.resource_planning_service.domains.PeriodoAcademico;
import com.skilling.lms.resource_planning_service.repositories.PeriodoAcademicoRepository;
import com.skilling.lms.resource_planning_service.service.PeriodoAcademicoService;
import com.skilling.lms.shared.dtos.resources_planning.request.PeriodoAcademicoRequestDTO;
import com.skilling.lms.shared.dtos.resources_planning.response.PeriodoAcademicoResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PeriodoAcademicoServiceImpl implements PeriodoAcademicoService {
    
    private final PeriodoAcademicoRepository periodoAcademicoRepository;

    private static final String PERIODO_ACADEMICO_SERVICE_CB = "periodoAcademicoServiceCB";

    @Override
    @CircuitBreaker(name = PERIODO_ACADEMICO_SERVICE_CB, fallbackMethod = "fallbackCreatePeriodoAcademico")
    public Mono<PeriodoAcademicoResponseDTO> createPeriodoAcademico(PeriodoAcademicoRequestDTO requestDTO) {
        PeriodoAcademico periodoAcademico = PeriodoAcademico.builder()
                .nombre(requestDTO.nombre())
                .fechaInicio(requestDTO.fechaInicio())
                .fechaFin(requestDTO.fechaFin())
                .estado(requestDTO.estado())
                .tipoPeriodo(requestDTO.tipoPeriodo())
                .build();

        return periodoAcademicoRepository.save(periodoAcademico)
                .map(this::toPeriodoAcademicoResponseDTO);
    }

    @SuppressWarnings("unused") 
    private Mono<PeriodoAcademicoResponseDTO> fallbackCreatePeriodoAcademico(PeriodoAcademicoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createPeriodoAcademico activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Periodo Académico no está disponible o falló al crear el periodo."));
    }

    @Override
    @CircuitBreaker(name = PERIODO_ACADEMICO_SERVICE_CB, fallbackMethod = "fallbackGetPeriodoAcademicoById")
    public Mono<PeriodoAcademicoResponseDTO> getPeriodoAcademicoById(UUID id) {
        return periodoAcademicoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Periodo Académico no encontrado con ID: " + id)))
                .map(this::toPeriodoAcademicoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PeriodoAcademicoResponseDTO> fallbackGetPeriodoAcademicoById(UUID id, Throwable t) {
        log.error("Fallback para getPeriodoAcademicoById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Periodo Académico no está disponible o falló al obtener el periodo."));
    }

    @Override
    @CircuitBreaker(name = PERIODO_ACADEMICO_SERVICE_CB, fallbackMethod = "fallbackGetAllPeriodosAcademicos")
    public Flux<PeriodoAcademicoResponseDTO> getAllPeriodosAcademicos() {
        return periodoAcademicoRepository.findAll()
                .map(this::toPeriodoAcademicoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<PeriodoAcademicoResponseDTO> fallbackGetAllPeriodosAcademicos(Throwable t) {
        log.error("Fallback para getAllPeriodosAcademicos activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = PERIODO_ACADEMICO_SERVICE_CB, fallbackMethod = "fallbackUpdatePeriodoAcademico")
    public Mono<PeriodoAcademicoResponseDTO> updatePeriodoAcademico(UUID id, PeriodoAcademicoRequestDTO requestDTO) {
        return periodoAcademicoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Periodo Académico no encontrado para actualizar con ID: " + id)))
                .flatMap(existingPeriodoAcademico -> {
                    existingPeriodoAcademico.setNombre(requestDTO.nombre());
                    existingPeriodoAcademico.setFechaInicio(requestDTO.fechaInicio());
                    existingPeriodoAcademico.setFechaFin(requestDTO.fechaFin());
                    existingPeriodoAcademico.setEstado(requestDTO.estado());
                    existingPeriodoAcademico.setTipoPeriodo(requestDTO.tipoPeriodo());
                    
                    return periodoAcademicoRepository.save(existingPeriodoAcademico);
                })
                .map(this::toPeriodoAcademicoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PeriodoAcademicoResponseDTO> fallbackUpdatePeriodoAcademico(UUID id, PeriodoAcademicoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updatePeriodoAcademico activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Periodo Académico no está disponible o falló al actualizar el periodo."));
    }

    @Override
    @CircuitBreaker(name = PERIODO_ACADEMICO_SERVICE_CB, fallbackMethod = "fallbackDeletePeriodoAcademico")
    public Mono<Void> deletePeriodoAcademico(UUID id) {
        return periodoAcademicoRepository.deleteById(id)
                .then(Mono.empty()); 
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeletePeriodoAcademico(UUID id, Throwable t) {
        log.error("Fallback para deletePeriodoAcademico activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Periodo Académico no está disponible o falló al eliminar el periodo."));
    }

    // -- Método Auxiliar para Mapeo Simple
    private PeriodoAcademicoResponseDTO toPeriodoAcademicoResponseDTO(PeriodoAcademico periodoAcademico) {
        return new PeriodoAcademicoResponseDTO(
            periodoAcademico.getId(),
            periodoAcademico.getNombre(),
            periodoAcademico.getFechaInicio(),
            periodoAcademico.getFechaFin(),
            periodoAcademico.getEstado(),
            periodoAcademico.getTipoPeriodo()
        );
    }
}
