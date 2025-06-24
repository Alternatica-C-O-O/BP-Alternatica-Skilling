package com.skilling.lms.enrollment_service.repositories;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.enrollment_service.domains.Inscripcion;
import com.skilling.lms.shared.models.enums.InscripcionEstado;

import reactor.core.publisher.Flux;

public interface InscripcionRepository extends ReactiveCrudRepository<Inscripcion, UUID> {
    
    Flux<Inscripcion> findByUsuariosId(UUID usuariosId);
    Flux<Inscripcion> findByCursoOfertadoId(UUID cursoOfertadoId);
    Flux<Inscripcion> findByEstado(InscripcionEstado estado);
    Flux<Inscripcion> findByFechaInscripcionBetween(LocalDate start, LocalDate end);
}
