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
@Table("publicacion_foro")
public class PublicacionForo {

    @Id
    private UUID id;

    private String contenido;

    @Column("fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    @Column("usuarios_id")
    private UUID usuariosId; // Foreign key

    @Column("foro_id")
    private UUID foroId; // Foreign key
    
    @Column("publicacion_foro_id")
    private UUID publicacionForoId; // Foreign key (para respuestas anidadas)
}
