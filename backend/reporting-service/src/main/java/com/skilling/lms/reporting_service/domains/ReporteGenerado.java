package com.skilling.lms.reporting_service.domains;

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
@Table("reporte_generado")
public class ReporteGenerado {

    @Id
    @Column("id")
    private UUID id;

    @Column("nombre_reporte")
    private String nombreReporte;

    @Column("fecha_generacion")
    private LocalDate fechaGeneracion;

    @Column("parametros_reporte")
    private String parametrosReporte;

    @Column("url_reporte")
    private String urlReporte;
    
    @Column("usuarios_id")
    private UUID usuariosId; // Foreign key
}
