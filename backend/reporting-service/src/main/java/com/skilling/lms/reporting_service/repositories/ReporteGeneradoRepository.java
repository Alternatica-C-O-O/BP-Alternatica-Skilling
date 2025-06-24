package com.skilling.lms.reporting_service.repositories;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.reporting_service.domains.ReporteGenerado;

import reactor.core.publisher.Flux;

public interface ReporteGeneradoRepository extends ReactiveCrudRepository<ReporteGenerado, UUID> {

    Flux<ReporteGenerado> findByUsuariosId(UUID usuariosId);
    Flux<ReporteGenerado> findByNombreReporte(String nombreReporte);
    Flux<ReporteGenerado> findByFechaGeneracionBetween(LocalDate start, LocalDate end);
}
