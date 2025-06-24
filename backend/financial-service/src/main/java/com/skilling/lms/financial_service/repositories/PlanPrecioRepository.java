package com.skilling.lms.financial_service.repositories;

import java.util.UUID;

import com.skilling.lms.financial_service.domains.PlanPrecio;
import com.skilling.lms.shared.models.enums.PlanPrecioTipo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanPrecioRepository {

    Mono<PlanPrecio> findByNombrePlan(String nombrePlan);
    Flux<PlanPrecio> findByCursoOfertadoId(UUID cursoOfertadoId);
    Flux<PlanPrecio> findByEstado(PlanPrecioTipo estado);
}
