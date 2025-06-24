package com.skilling.lms.resource_planning_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.resource_planning_service.domains.PlataformaVirtual;

public interface PlataformaVirtualRepository extends ReactiveCrudRepository<PlataformaVirtual, UUID> {

}
