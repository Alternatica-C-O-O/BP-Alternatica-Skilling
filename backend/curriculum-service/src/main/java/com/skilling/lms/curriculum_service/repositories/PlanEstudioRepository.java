package com.skilling.lms.curriculum_service.repositories;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.curriculum_service.domains.PlanEstudio;
import com.skilling.lms.shared.models.enums.GeneralEstado;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanEstudioRepository extends ReactiveCrudRepository<PlanEstudio, UUID> {

    Mono<PlanEstudio> findByNombrePlanAndVersion(String nombrePlan, String version);
    Flux<PlanEstudio> findByEstado(GeneralEstado estado);
    Flux<PlanEstudio> findByModeloEducativoId(UUID modeloEducativoId);
    Flux<PlanEstudio> findByFechaAprobacionBetween(LocalDate startDate, LocalDate endDate);
}
