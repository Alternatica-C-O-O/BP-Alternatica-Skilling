package com.skilling.lms.resource_planning_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.resource_planning_service.domains.PlataformaVirtual;
import com.skilling.lms.shared.models.enums.PlataformaVirtualTipo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlataformaVirtualRepository extends ReactiveCrudRepository<PlataformaVirtual, UUID> {
    
    Mono<PlataformaVirtual> findByNombrePlataforma(String nombrePlataforma);
    Flux<PlataformaVirtual> findByTipo(PlataformaVirtualTipo tipo);
}
