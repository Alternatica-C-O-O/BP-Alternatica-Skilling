package com.skilling.lms.enrollment_service.domains;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.InscripcionEstado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("inscripcion")
public class Inscripcion {

    @Id
    @Column("id")
    private UUID id;

    @Column("fecha_inscripcion")
    private LocalDate fechaInscripcion;

    private InscripcionEstado estado;

    @Column("fecha_finalizacion")
    private LocalDate fechaFinalizacion;

    @Column("progreso_porcentaje")
    private Double progresoPorcentaje;

    @Column("usuarios_id")
    private UUID usuariosId; // Foreign key

    @Column("curso_ofertado_id")
    private UUID cursoOfertadoId; // Foreign key
}
