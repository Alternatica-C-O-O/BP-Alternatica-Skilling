package com.skilling.lms.financial_service.service;

import java.util.UUID;

import com.skilling.lms.shared.dtos.financial.request.PlanPrecioRequestDTO;
import com.skilling.lms.shared.dtos.financial.response.PlanPrecioResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanPrecioService {

    Mono<PlanPrecioResponseDTO> createPlanPrecio(PlanPrecioRequestDTO requestDTO);
    Mono<PlanPrecioResponseDTO> getPlanPrecioById(UUID id);
    Flux<PlanPrecioResponseDTO> getAllPlanesPrecio();
    Mono<PlanPrecioResponseDTO> updatePlanPrecio(UUID id, PlanPrecioRequestDTO requestDTO);
    Mono<Void> deletePlanPrecio(UUID id);
}
