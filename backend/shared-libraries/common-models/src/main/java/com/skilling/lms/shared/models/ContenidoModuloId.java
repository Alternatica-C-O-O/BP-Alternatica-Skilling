package com.skilling.lms.shared.models;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContenidoModuloId implements Serializable {

    @Column("recurso_didactico_id")
    private UUID recursoDidacticoId;

    @Column("modulo_id")
    private UUID moduloId;
}
