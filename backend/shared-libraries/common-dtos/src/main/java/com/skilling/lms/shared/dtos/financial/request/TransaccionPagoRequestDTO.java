package com.skilling.lms.shared.dtos.financial.request;

import java.math.BigDecimal;
import java.util.UUID;

import com.skilling.lms.shared.models.enums.MetodoPagoTipo;
import com.skilling.lms.shared.models.enums.PagoEstado;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TransaccionPagoRequestDTO(
    @NotNull(message = "El monto pagado no puede ser nulo")
    @DecimalMin(value = "0.00", inclusive = true, message = "El monto pagado debe ser positivo o cero")
    BigDecimal montoPagado,

    @NotNull(message = "El método de pago no puede ser nulo")
    MetodoPagoTipo metodoPago,

    @NotNull(message = "El estado del pago no puede ser nulo")
    PagoEstado estadoPago,

    @NotBlank(message = "La referencia externa no puede estar vacía")
    @Size(max = 255, message = "La referencia externa no puede exceder 255 caracteres")
    String referenciaExterna,

    @NotNull(message = "El ID de usuario no puede ser nulo")
    UUID usuariosId,

    @NotNull(message = "El ID del plan de precio no puede ser nulo")
    UUID planPrecioId,

    UUID cursoOfertadoId 
) {}
