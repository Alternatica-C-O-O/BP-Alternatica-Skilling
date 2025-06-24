package com.skilling.lms.curriculum_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.curriculum_service.domains.ModeloEducativo;
import com.skilling.lms.shared.models.enums.GeneralEstado;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ModeloEducativoRepository extends ReactiveCrudRepository<ModeloEducativo, UUID> {

    Mono<ModeloEducativo> findByNombreModelo(String nombreModelo);
    Flux<ModeloEducativo> findByEstado(GeneralEstado estado);
    Flux<ModeloEducativo> findByUsuariosId(UUID usuariosId);
}
