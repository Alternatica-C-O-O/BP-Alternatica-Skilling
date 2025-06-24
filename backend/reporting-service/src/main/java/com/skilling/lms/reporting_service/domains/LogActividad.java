package com.skilling.lms.reporting_service.domains;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.EventoTipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("log_actividad")
public class LogActividad {

    @Id
    @Column("id")
    private UUID id;

    @Column("fecha_hora")
    private LocalDateTime fechaHora;

    @Column("tipo_evento")
    private EventoTipo tipoEvento;

    @Column("detalle_evento")
    private String detalleEvento;

    @Column("ip_origen")
    private String ipOrigen;
    
    @Column("usuarios_id")
    private UUID usuariosId; // Foreign key
}
