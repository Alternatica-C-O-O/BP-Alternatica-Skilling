package com.skilling.lms.resource_planning_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.resource_planning_service.domains.EspacioFisico;

public interface EspacioFisicoRepository extends ReactiveCrudRepository<EspacioFisico, UUID> {

}
