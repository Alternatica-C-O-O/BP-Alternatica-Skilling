package com.skilling.lms.enrollment_service.domains;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.AsistenciaEstado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("asistencia")
public class Asistencia {

    @Id
    @Column("id")
    private UUID id;

    @Column("fecha_clase")
    private LocalDate fechaClase;

    @Column("estado_asistencia")
    private AsistenciaEstado estadoAsistencia;

    @Column("hora_registro")
    private LocalTime horaRegistro;
    
    @Column("inscripcion_id")
    private UUID inscripcionId; // Foreign key
}
