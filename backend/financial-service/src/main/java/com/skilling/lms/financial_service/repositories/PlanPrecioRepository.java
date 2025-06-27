package com.skilling.lms.financial_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.financial_service.domains.PlanPrecio;
import com.skilling.lms.shared.models.enums.PlanPrecioTipo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanPrecioRepository extends ReactiveCrudRepository<PlanPrecio, UUID> {

    Mono<PlanPrecio> findByNombrePlan(String nombrePlan);
    Flux<PlanPrecio> findByCursoOfertadoId(UUID cursoOfertadoId);
    Flux<PlanPrecio> findByEstado(PlanPrecioTipo estado);
}
