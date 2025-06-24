package com.skilling.lms.resource_planning_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.resource_planning_service.domains.AsignacionHorario;

public interface AsignacionHorarioRepository extends ReactiveCrudRepository<AsignacionHorario, UUID> {

}
