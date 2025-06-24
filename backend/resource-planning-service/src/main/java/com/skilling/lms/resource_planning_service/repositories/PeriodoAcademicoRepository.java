package com.skilling.lms.resource_planning_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.resource_planning_service.domains.PeriodoAcademico;

public interface PeriodoAcademicoRepository extends ReactiveCrudRepository<PeriodoAcademico, UUID> {

}
