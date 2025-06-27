package com.skilling.lms.shared.dtos.financial.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.MetodoPagoTipo;
import com.skilling.lms.shared.models.enums.PagoEstado;

public record TransaccionPagoResponseDTO(
    UUID id,
    BigDecimal montoPagado,
    LocalDateTime fechaTransaccion,
    MetodoPagoTipo metodoPago,
    PagoEstado estadoPago,
    String referenciaExterna,
    UUID usuario,        
    UUID planPrecio,  
    UUID cursoOfertado
) {}
