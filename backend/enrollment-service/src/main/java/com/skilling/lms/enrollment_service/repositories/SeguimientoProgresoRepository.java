package com.skilling.lms.enrollment_service.repositories;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.enrollment_service.domains.SeguimientoProgreso;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SeguimientoProgresoRepository extends ReactiveCrudRepository<SeguimientoProgreso, UUID> {
    
    Mono<SeguimientoProgreso> findByInscripcionIdAndModuloId(UUID inscripcionId, UUID moduloId);
    Flux<SeguimientoProgreso> findByInscripcionId(UUID inscripcionId);
    Flux<SeguimientoProgreso> findByModuloId(UUID moduloId);
    Flux<SeguimientoProgreso> findByCompletado(Boolean completado);
    Flux<SeguimientoProgreso> findByFechaUltimoAccesoBetween(LocalDateTime start, LocalDateTime end);
}
