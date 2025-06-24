package com.skilling.lms.financial_service.domains;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.PagoEstado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("transaccion_pago")
public class TransaccionPago {

    @Id
    @Column("id")
    private UUID id;

    @Column("monto_pagado")
    private BigDecimal montoPagado;

    @Column("fecha_transaccion")
    private LocalDateTime fechaTransaccion;

    @Column("metodo_pago")
    private String metodoPago;

    @Column("estado_pago")
    private PagoEstado estadoPago;

    @Column("referencia_externa")
    private String referenciaExterna;

    @Column("usuarios_id")
    private UUID usuariosId; // Foreign key

    @Column("plan_precio_id")
    private UUID planPrecioId; // Foreign key
    
    @Column("curso_ofertado_id")
    private UUID cursoOfertadoId; // Foreign key, puede ser null
}
