package com.skilling.lms.shared.models;

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
    private ContenidoModuloId id;
    
    @Column("orden_modulo")
    private Integer ordenModulo; // Columna adicional
    
    // @Transient private RecursoDidactico recursoDidactico;
    // @Transient private Modulo modulo;
}
