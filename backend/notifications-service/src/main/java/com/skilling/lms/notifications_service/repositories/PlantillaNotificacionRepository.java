package com.skilling.lms.notifications_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.notifications_service.domains.PlantillaNotificacion;

import reactor.core.publisher.Mono;

public interface PlantillaNotificacionRepository extends ReactiveCrudRepository<PlantillaNotificacion, UUID> {
    
    Mono<PlantillaNotificacion> findByNombreInterno(String nombreInterno);
}
