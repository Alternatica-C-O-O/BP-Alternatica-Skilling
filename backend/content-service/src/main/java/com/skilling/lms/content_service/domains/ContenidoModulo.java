package com.skilling.lms.content_service.domains;

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
@Table("contenido_modulo")
public class ContenidoModulo {
    
    @Id
    @Column("id")
    private UUID id;

    @Column("orden_modulo")
    private Integer ordenModulo;

    @Column("recurso_didactico_id")
    private UUID recursoDidacticoId;

    @Column("modulo_id")
    private UUID moduloId;
}
