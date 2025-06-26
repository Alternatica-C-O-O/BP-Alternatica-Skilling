package com.skilling.lms.resource_planning_service.repositories;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.resource_planning_service.domains.PeriodoAcademico;
import com.skilling.lms.shared.models.enums.PeriodoAcademicoEstado;
import com.skilling.lms.shared.models.enums.PeriodoAcademicoTipo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PeriodoAcademicoRepository extends ReactiveCrudRepository<PeriodoAcademico, UUID> {

    Mono<PeriodoAcademico> findByNombre(String nombre);

    Flux<PeriodoAcademico> findByEstado(PeriodoAcademicoEstado estado);

    Flux<PeriodoAcademico> findByTipoPeriodo(PeriodoAcademicoTipo tipoPeriodo);

    Flux<PeriodoAcademico> findByFechaInicioBetween(LocalDate start, LocalDate end);
}
