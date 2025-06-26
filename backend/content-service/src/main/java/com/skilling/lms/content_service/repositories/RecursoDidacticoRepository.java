package com.skilling.lms.content_service.repositories;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.content_service.domains.RecursoDidactico;
import com.skilling.lms.shared.models.enums.ArchivoTipo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecursoDidacticoRepository extends ReactiveCrudRepository<RecursoDidactico, UUID> {

    @Query("""
        INSERT INTO recurso_didactico 
        (id, nombre_archivo, tipo_archivo, url, fecha_subida, metadata, version, es_activo, usuarios_id) 
        VALUES 
        (:id, :nombreArchivo, :tipoArchivo, :url, :fechaSubida, CAST(:metadata AS json), :version, :esActivo, :usuariosId)
        RETURNING *
        """)
    Mono<RecursoDidactico> customInsert(
            @Param("id") UUID id,
            @Param("nombreArchivo") String nombreArchivo,
            @Param("tipoArchivo") String tipoArchivo,
            @Param("url") String url,
            @Param("fechaSubida") LocalDate fechaSubida,
            @Param("metadata") String metadata,
            @Param("version") Integer version,
            @Param("esActivo") Boolean esActivo,
            @Param("usuariosId") UUID usuariosId
    );

    Flux<RecursoDidactico> findByTipoArchivo(ArchivoTipo tipoArchivo);
    Flux<RecursoDidactico> findByUsuariosId(UUID usuariosId);
    Flux<RecursoDidactico> findByEsActivo(Boolean esActivo);
}
