package com.skilling.lms.notifications_service.repositories;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.notifications_service.domains.Notificacion;
import com.skilling.lms.shared.models.enums.NotificacionTipo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotificacionRepository extends ReactiveCrudRepository<Notificacion, UUID> {
    
    Flux<Notificacion> findByUsuariosId(UUID usuariosId);
    Flux<Notificacion> findByTipoNotificacion(NotificacionTipo tipoNotificacion); 
    Flux<Notificacion> findByEstaLeida(Boolean estaLeida);
    Flux<Notificacion> findByFechaEnvioBetween(LocalDateTime start, LocalDateTime end);

    public interface NotificacionWithPlantillaProjection {
        UUID getId();
        @Column("tipo_notificacion") NotificacionTipo getTipoNotificacion();
        String getTitulo();
        String getContenido();
        @Column("fecha_envio") LocalDateTime getFechaEnvio();
        @Column("fecha_lectura") LocalDateTime getFechaLectura();
        @Column("esta_leida") Boolean getEstaLeida();
        @Column("url_accion") String getUrlAccion();
        @Column("evento_origen") String getEventoOrigen();
        @Column("referencia_id") String getReferenciaId();
        @Column("usuarios_id") UUID getUsuariosId();
        @Column("plantilla_id") UUID getPlantillaId();
        @Column("nombre_interno") String getNombreInterno();
        @Column("asunto_plantilla") String getAsuntoPlantilla();
        @Column("cuerpo_plantilla") String getCuerpoPlantilla();
        @Column("variables_disponibles") String getVariablesDisponibles();
        @Column("canal_preferido") String getCanalPreferido();
    }

    @Query("SELECT n.id, n.tipo_notificacion, n.titulo, n.contenido, n.fecha_envio, n.fecha_lectura, n.esta_leida, n.url_accion, n.evento_origen, n.referencia_id, n.usuarios_id, " +
           "p.id as plantilla_id, p.nombre_interno, p.asunto_plantilla, p.cuerpo_plantilla, p.variables_disponibles, p.canal_preferido " +
           "FROM notificaciones n " +
           "JOIN plantilla_notificaciones p ON n.plantilla_notificaciones_id = p.id " +
           "WHERE n.id = :notificacionId")
    Mono<NotificacionWithPlantillaProjection> findNotificacionWithPlantillaById(UUID notificacionId);
}
