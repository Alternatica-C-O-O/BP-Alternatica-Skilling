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
@Table("mensaje")
public class Mensaje {

    @Id
    @Column("id")
    private UUID id;

    private String asunto;
    private String contenido;

    @Column("fecha_envio")
    private LocalDateTime fechaEnvio;

    private Boolean leido;

    @Column("usuarios_id")
    private UUID usuariosId; // Foreign key
    
    @Column("foro_id")
    private UUID foroId; // Foreign key
}
