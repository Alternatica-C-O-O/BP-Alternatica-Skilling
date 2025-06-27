package com.skilling.lms.financial_service.service.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.financial_service.domains.PlanPrecio;
import com.skilling.lms.financial_service.repositories.PlanPrecioRepository;
import com.skilling.lms.financial_service.service.PlanPrecioService;
import com.skilling.lms.shared.dtos.financial.request.PlanPrecioRequestDTO;
import com.skilling.lms.shared.dtos.financial.response.PlanPrecioResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlanPrecioServiceImpl implements PlanPrecioService {

    private final PlanPrecioRepository planPrecioRepository;

    private static final String PLAN_PRECIO_SERVICE_CB = "planPrecioServiceCB";
    
    @Override
    @CircuitBreaker(name = PLAN_PRECIO_SERVICE_CB, fallbackMethod = "fallbackCreatePlanPrecio")
    public Mono<PlanPrecioResponseDTO> createPlanPrecio(PlanPrecioRequestDTO requestDTO) {

        PlanPrecio planPrecio = PlanPrecio.builder()
                .nombrePlan(requestDTO.nombrePlan())
                .version(requestDTO.version())
                .fechaAprobacion(requestDTO.fechaAprobacion())
                .estado(requestDTO.estado())
                .fechaUltimaActualizacion(LocalDate.now()) 
                .cursoOfertadoId(requestDTO.cursoOfertadoId())
                .build();

        return planPrecioRepository.save(planPrecio)
                .map(this::toPlanPrecioResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PlanPrecioResponseDTO> fallbackCreatePlanPrecio(
            PlanPrecioRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createPlanPrecio activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Planes de Precio no está disponible o falló al crear el plan."));
    }

    @Override
    @CircuitBreaker(name = PLAN_PRECIO_SERVICE_CB, fallbackMethod = "fallbackGetPlanPrecioById")
    public Mono<PlanPrecioResponseDTO> getPlanPrecioById(UUID id) {
        return planPrecioRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Plan de Precio no encontrado con ID: " + id)))
                .map(this::toPlanPrecioResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PlanPrecioResponseDTO> fallbackGetPlanPrecioById(UUID id, Throwable t) {
        log.error("Fallback para getPlanPrecioById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Planes de Precio no está disponible o falló al obtener el plan."));
    }

    @Override
    @CircuitBreaker(name = PLAN_PRECIO_SERVICE_CB, fallbackMethod = "fallbackGetAllPlanesPrecio")
    public Flux<PlanPrecioResponseDTO> getAllPlanesPrecio() {
        return planPrecioRepository.findAll()
                .map(this::toPlanPrecioResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<PlanPrecioResponseDTO> fallbackGetAllPlanesPrecio(Throwable t) {
        log.error("Fallback para getAllPlanesPrecio activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = PLAN_PRECIO_SERVICE_CB, fallbackMethod = "fallbackUpdatePlanPrecio")
    public Mono<PlanPrecioResponseDTO> updatePlanPrecio(UUID id,
            PlanPrecioRequestDTO requestDTO) {

        return planPrecioRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Plan de Precio no encontrado para actualizar con ID: " + id)))
                .flatMap(existingPlanPrecio -> {
                    existingPlanPrecio.setNombrePlan(requestDTO.nombrePlan());
                    existingPlanPrecio.setVersion(requestDTO.version());
                    existingPlanPrecio.setFechaAprobacion(requestDTO.fechaAprobacion());
                    existingPlanPrecio.setEstado(requestDTO.estado());
                    existingPlanPrecio.setFechaUltimaActualizacion(LocalDate.now()); // Actualiza la fecha al modificar
                    existingPlanPrecio.setCursoOfertadoId(requestDTO.cursoOfertadoId());

                    return planPrecioRepository.save(existingPlanPrecio);
                })
                .map(this::toPlanPrecioResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<PlanPrecioResponseDTO> fallbackUpdatePlanPrecio(
            UUID id, PlanPrecioRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updatePlanPrecio activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Planes de Precio no está disponible o falló al actualizar el plan."));
    }

    @Override
    @CircuitBreaker(name = PLAN_PRECIO_SERVICE_CB, fallbackMethod = "fallbackDeletePlanPrecio")
    public Mono<Void> deletePlanPrecio(UUID id) {
        return planPrecioRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeletePlanPrecio(UUID id, Throwable t) {
        log.error("Fallback para deletePlanPrecio activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Planes de Precio no está disponible o falló al eliminar el plan."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private PlanPrecioResponseDTO toPlanPrecioResponseDTO(PlanPrecio planPrecio) {
        return new PlanPrecioResponseDTO(
            planPrecio.getId(),
            planPrecio.getNombrePlan(),
            planPrecio.getVersion(),
            planPrecio.getFechaAprobacion(),
            planPrecio.getEstado(),
            planPrecio.getFechaUltimaActualizacion(),
            planPrecio.getCursoOfertadoId() 
        );
    }
}
