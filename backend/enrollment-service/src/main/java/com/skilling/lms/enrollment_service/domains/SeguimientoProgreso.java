package com.skilling.lms.enrollment_service.domains;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("seguimiento_progreso")
public class SeguimientoProgreso {

    @Id
    @Column("id")
    private UUID id;

    @Column("fecha_ultimo_acceso")
    private LocalDateTime fechaUltimoAcceso;

    private Boolean completado;

    @Column("puntaje_obtenido_modulo")
    private Double puntajeObtenidoModulo;

    @Column("inscripcion_id")
    private UUID inscripcionId; // Foreign key
    
    @Column("modulo_id")
    private UUID moduloId; // Foreign key
}
