package com.skilling.lms.content_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.content_service.domains.ContenidoModulo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContenidoModuloRepository extends ReactiveCrudRepository<ContenidoModulo, UUID> {

    Flux<ContenidoModulo> findByModuloIdOrderByOrdenModulo(UUID moduloId);
    Mono<ContenidoModulo> findByModuloIdAndOrdenModulo(UUID moduloId, Integer ordenModulo);
    Mono<ContenidoModulo> findByModuloIdAndRecursoDidacticoId(UUID moduloId, UUID recursoDidacticoId);
}
