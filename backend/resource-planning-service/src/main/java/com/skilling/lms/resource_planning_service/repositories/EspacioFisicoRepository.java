package com.skilling.lms.resource_planning_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.resource_planning_service.domains.EspacioFisico;
import com.skilling.lms.shared.models.enums.EspacioTipo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EspacioFisicoRepository extends ReactiveCrudRepository<EspacioFisico, UUID> {

    Mono<EspacioFisico> findByNombre(String nombre);
    Flux<EspacioFisico> findByTipoEspacio(EspacioTipo tipoEspacio); 
    Flux<EspacioFisico> findByCapacidadGreaterThanEqual(Integer capacidad);
}
