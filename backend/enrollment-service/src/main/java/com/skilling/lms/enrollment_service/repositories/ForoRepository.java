package com.skilling.lms.enrollment_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.enrollment_service.domains.Foro;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ForoRepository extends ReactiveCrudRepository<Foro, UUID> {
    
    Mono<Foro> findByNombreForoAndCursoOfertadoId(String nombreForo, UUID cursoOfertadoId);
    Flux<Foro> findByCursoOfertadoId(UUID cursoOfertadoId);
}
