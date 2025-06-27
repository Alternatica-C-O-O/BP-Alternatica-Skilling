package com.skilling.lms.content_service.domains;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.ArchivoTipo;

import io.r2dbc.postgresql.codec.Json;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("recurso_didactico")
public class RecursoDidactico {

    @Id
    @Column("id")
    private UUID id;

    @Column("nombre_archivo")
    private String nombreArchivo;

    @Column("tipo_archivo")
    private ArchivoTipo tipoArchivo;

    private String url;

    @Column("fecha_subida")
    private LocalDate fechaSubida;

    private Json metadata; // JSON se mapea a String
    private Integer version;

    @Column("es_activo")
    private Boolean esActivo;
    
    @Column("usuarios_id")
    private UUID usuariosId; // Foreign key
}
