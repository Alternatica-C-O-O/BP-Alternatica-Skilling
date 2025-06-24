package com.skilling.lms.notifications_service.domains;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.NotificacionTipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("notificaciones")
public class Notificacion {

    @Id
    @Column("id")
    private UUID id;

    @Column("tipo_notificacion")    
    private NotificacionTipo tipoNotificacion;
    
    private String titulo;
    private String contenido;
    
    @Column("fecha_envio")
    private LocalDateTime fechaEnvio;
    
    @Column("fecha_lectura")
    private LocalDateTime fechaLectura;
    
    @Column("esta_leida")
    private Boolean estaLeida;
    
    @Column("url_accion")
    private String urlAccion;
    
    @Column("evento_origen")
    private String eventoOrigen;
    
    @Column("referencia_id")
    private String referenciaId;
    
    @Column("usuarios_id")
    private UUID usuariosId;
    
    @Column("plantilla_notificaciones_id")
    private UUID plantillaNotificacionesId;
}
