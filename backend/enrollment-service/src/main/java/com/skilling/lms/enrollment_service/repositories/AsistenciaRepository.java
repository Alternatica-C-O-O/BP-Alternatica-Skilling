package com.skilling.lms.enrollment_service.repositories;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.enrollment_service.domains.Asistencia;
import com.skilling.lms.shared.models.enums.AsistenciaEstado;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AsistenciaRepository extends ReactiveCrudRepository<Asistencia, UUID> {
    
    Mono<Asistencia> findByInscripcionIdAndFechaClase(UUID inscripcionId, LocalDate fechaClase);
    Flux<Asistencia> findByInscripcionId(UUID inscripcionId);
    Flux<Asistencia> findByFechaClase(LocalDate fechaClase);
    Flux<Asistencia> findByEstadoAsistencia(AsistenciaEstado estadoAsistencia);
}
