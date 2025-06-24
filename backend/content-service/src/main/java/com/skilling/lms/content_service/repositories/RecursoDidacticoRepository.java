package com.skilling.lms.content_service.repositories;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.content_service.domains.RecursoDidactico;
import com.skilling.lms.shared.models.enums.ArchivoTipo;

import reactor.core.publisher.Flux;

public interface RecursoDidacticoRepository extends ReactiveCrudRepository<RecursoDidactico, UUID> {

    Flux<RecursoDidactico> findByTipoArchivo(ArchivoTipo tipoArchivo);
    Flux<RecursoDidactico> findByUsuariosId(UUID usuariosId);
    Flux<RecursoDidactico> findByEsActivo(Boolean esActivo);
}
