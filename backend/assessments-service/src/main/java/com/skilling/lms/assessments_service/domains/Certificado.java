package com.skilling.lms.assessments_service.domains;

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
@Table("certificado")
public class Certificado {

    @Id
    @Column("id")
    private UUID id;

    @Column("fecha_emision")
    private LocalDate fechaEmision;

    @Column("url_certificado")
    private String urlCertificado;

    @Column("codigo_verificacion")
    private String codigoVerificacion;
    
    @Column("inscripcion_id")
    private UUID inscripcionId; // Foreign key
}
