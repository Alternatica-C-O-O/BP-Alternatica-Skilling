package com.skilling.lms.curriculum_service.service.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.curriculum_service.domains.PlanEstudio;
import com.skilling.lms.curriculum_service.repositories.PlanEstudioRepository;
import com.skilling.lms.curriculum_service.service.PlanEstudioService;
import com.skilling.lms.shared.dtos.curriculum.request.PlanEstudioRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.PlanEstudioResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlanEstudioServiceImpl implements PlanEstudioService {

    private final PlanEstudioRepository planEstudioRepository;

    private static final String PLAN_ESTUDIO_SERVICE_CB = "planEstudioServiceCB";

    @Override
    @CircuitBreaker(name = PLAN_ESTUDIO_SERVICE_CB, fallbackMethod = "fallbackCreatePlanEstudio")
    public Mono<PlanEstudioResponseDTO> createPlanEstudio(PlanEstudioRequestDTO requestDTO) {
        return planEstudioRepository.findByNombrePlanAndVersion(requestDTO.nombrePlan(), requestDTO.version())
                .flatMap(existingPlan -> {
                    log.warn("Intento de crear plan de estudio duplicado: Nombre '{}', Versión '{}'",
                            requestDTO.nombrePlan(), requestDTO.version());
                    return Mono.error(new RuntimeException("Ya existe un plan de estudio con este nombre y versión."));
                })
                .then(Mono.defer(() -> {
                    PlanEstudio planEstudio = PlanEstudio.builder()
                            .nombrePlan(requestDTO.nombrePlan())
                            .version(requestDTO.version())
                            .fechaAprobacion(requestDTO.fechaAprobacion())
                            .estado(requestDTO.estado())
                            .fechaUltimaActualizacion(LocalDate.now()) 
                            .modeloEducativoId(requestDTO.modeloEducativoId())
                            .build();

                    return planEstudioRepository.save(planEstudio)
                            .map(this::toPlanEstudioResponseDTO);
                }));
    }

    @SuppressWarnings("unused")
    private Mono<PlanEstudioResponseDTO> fallbackCreatePlanEstudio(
            PlanEstudioRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createPlanEstudio activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Planes de Estudio no está disponible o falló al crear el plan."));
    }

    @Override
    @CircuitBreaker(name = PLAN_ESTUDIO_SERVICE_CB, fallbackMethod = "fallbackGetPlanEstudioById")
    public Mono<PlanEstudioResponseDTO> getPlanEstudioById(UUID id) {
        return planEstudioRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Plan de Estudio no encontrado con ID: " + id)))
                .map(this::toPlanEstudioResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PlanEstudioResponseDTO> fallbackGetPlanEstudioById(UUID id, Throwable t) {
        log.error("Fallback para getPlanEstudioById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Planes de Estudio no está disponible o falló al obtener el plan."));
    }

    @Override
    @CircuitBreaker(name = PLAN_ESTUDIO_SERVICE_CB, fallbackMethod = "fallbackGetAllPlanesEstudio")
    public Flux<PlanEstudioResponseDTO> getAllPlanesEstudio() {
        return planEstudioRepository.findAll()
                .map(this::toPlanEstudioResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<PlanEstudioResponseDTO> fallbackGetAllPlanesEstudio(Throwable t) {
        log.error("Fallback para getAllPlanesEstudio activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = PLAN_ESTUDIO_SERVICE_CB, fallbackMethod = "fallbackUpdatePlanEstudio")
    public Mono<PlanEstudioResponseDTO> updatePlanEstudio(UUID id, PlanEstudioRequestDTO requestDTO) {
        return planEstudioRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Plan de Estudio no encontrado para actualizar con ID: " + id)))
                .flatMap(existingPlan -> {
                    Mono<Void> checkDuplicate = Mono.empty();
                    if (!existingPlan.getNombrePlan().equals(requestDTO.nombrePlan()) ||
                        !existingPlan.getVersion().equals(requestDTO.version())) {
                        checkDuplicate = planEstudioRepository.findByNombrePlanAndVersion(requestDTO.nombrePlan(), requestDTO.version())
                            .flatMap(p -> {
                                if (!p.getId().equals(id)) { 
                                    log.warn("Intento de actualizar a nombre y versión duplicados: Nombre '{}', Versión '{}'",
                                            requestDTO.nombrePlan(), requestDTO.version());
                                    return Mono.error(new RuntimeException("Ya existe otro plan de estudio con este nombre y versión."));
                                }
                                return Mono.empty();
                            })
                            .then();
                    }

                    return checkDuplicate.then(Mono.defer(() -> {
                        existingPlan.setNombrePlan(requestDTO.nombrePlan());
                        existingPlan.setVersion(requestDTO.version());
                        existingPlan.setFechaAprobacion(requestDTO.fechaAprobacion());
                        existingPlan.setEstado(requestDTO.estado());
                        existingPlan.setFechaUltimaActualizacion(LocalDate.now()); 
                        existingPlan.setModeloEducativoId(requestDTO.modeloEducativoId());

                        return planEstudioRepository.save(existingPlan);
                    }));
                })
                .map(this::toPlanEstudioResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PlanEstudioResponseDTO> fallbackUpdatePlanEstudio(
            UUID id, PlanEstudioRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updatePlanEstudio activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Planes de Estudio no está disponible o falló al actualizar el plan."));
    }

    @Override
    @CircuitBreaker(name = PLAN_ESTUDIO_SERVICE_CB, fallbackMethod = "fallbackDeletePlanEstudio")
    public Mono<Void> deletePlanEstudio(UUID id) {
        return planEstudioRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeletePlanEstudio(UUID id, Throwable t) {
        log.error("Fallback para deletePlanEstudio activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Planes de Estudio no está disponible o falló al eliminar el plan."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private PlanEstudioResponseDTO toPlanEstudioResponseDTO(PlanEstudio planEstudio) {
        return new PlanEstudioResponseDTO(
            planEstudio.getId(),
            planEstudio.getNombrePlan(),
            planEstudio.getVersion(),
            planEstudio.getFechaAprobacion(),
            planEstudio.getEstado(),
            planEstudio.getFechaUltimaActualizacion(),
            planEstudio.getModeloEducativoId()
        );
    }
}
