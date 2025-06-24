package com.skilling.lms.enrollment_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.enrollment_service.domains.SeguimientoProgreso;

import reactor.core.publisher.Flux;

public interface SeguimientoProgresoRepository extends ReactiveCrudRepository<SeguimientoProgreso, UUID> {
    
    Flux<SeguimientoProgreso> findByInscripcionId(UUID inscripcionId);
    Flux<SeguimientoProgreso> findByModuloId(UUID moduloId);
    Flux<SeguimientoProgreso> findByCompletado(Boolean completado);
}
