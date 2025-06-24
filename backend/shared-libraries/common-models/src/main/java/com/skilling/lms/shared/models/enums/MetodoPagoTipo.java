package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MetodoPagoTipo {
    TARJETA_CREDITO("TARJETA DE CREDITO"),
    TARJETA_DEBITO("TARJETA DE DEBITO"),
    EFECTIVO("EFECTIVO"),
    TRANSFERENCIA_BANCARIA("TRANSFERENCIA BANCARIA"),
    PAYPAL("PAYPAL"),
    MERCADOPAGO("MERCADO PAGO"),
    OTRO("OTRO");

    private final String value;

    MetodoPagoTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static MetodoPagoTipo fromValue(String text) {
        for (MetodoPagoTipo b : MetodoPagoTipo.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
