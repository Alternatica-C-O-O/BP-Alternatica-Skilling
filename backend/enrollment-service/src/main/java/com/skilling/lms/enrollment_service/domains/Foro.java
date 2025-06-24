package com.skilling.lms.enrollment_service.domains;

import java.time.LocalDate;
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
@Table("foro")
public class Foro {

    @Id
    @Column("id")
    private UUID id;

    @Column("nombre_foro")
    private String nombreForo;

    private String descripcion;

    @Column("fecha_creacion")
    private LocalDate fechaCreacion;
    
    @Column("curso_ofertado_id")
    private UUID cursoOfertadoId; // Foreign key
}
