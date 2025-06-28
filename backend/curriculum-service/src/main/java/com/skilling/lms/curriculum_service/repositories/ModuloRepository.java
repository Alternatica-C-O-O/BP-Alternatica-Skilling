package com.skilling.lms.curriculum_service.repositories;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.curriculum_service.domains.Modulo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ModuloRepository extends ReactiveCrudRepository<Modulo, UUID> {

    Flux<Modulo> findByCursoOfertadoId(UUID cursoOfertadoId);
    Flux<Modulo> findByCursoOfertadoIdOrderByOrdenAsc(UUID cursoOfertadoId);

    public interface ModuloWithContenidosProjection {
        UUID getId();
        @Column("nombre_modulo") String getNombreModulo();
        Integer getOrden();
        String getDescripcion();
        @Column("objetivos_aprendizaje") String getObjetivosAprendizaje();
        @Column("curso_ofertado_id") UUID getCursoOfertadoId();
        @Column("recurso_id") UUID getRecursoId();
        @Column("nombre_archivo") String getNombreArchivo();
        @Column("tipo_archivo") String getTipoArchivo();
        @Column("url_recurso") String getUrlRecurso();
        @Column("orden_modulo") Integer getOrdenModulo();
    }

    @Query("SELECT m.id, m.nombre_modulo, m.orden, m.descripcion, m.objetivos_aprendizaje, m.curso_ofertado_id, " +
           "rd.id as recurso_id, rd.nombre_archivo, rd.tipo_archivo, rd.url as url_recurso, cm.orden_modulo " +
           "FROM modulo m " +
           "LEFT JOIN contenido_modulo cm ON m.id = cm.modulo_id " +
           "LEFT JOIN recurso_didactico rd ON cm.recurso_didactico_id = rd.id " +
           "WHERE m.id = :moduloId " +
           "ORDER BY cm.orden_modulo ASC")
    Flux<ModuloWithContenidosProjection> findModuloWithContenidosById(UUID moduloId);

    Mono<Modulo> findByNombreModuloAndCursoOfertadoId(String nombreModulo, UUID cursoOfertadoId);
    Mono<Modulo> findByOrdenAndCursoOfertadoId(Integer orden, UUID cursoOfertadoId);
}
