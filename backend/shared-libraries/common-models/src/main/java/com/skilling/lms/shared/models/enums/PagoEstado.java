package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PagoEstado {
    PENDIENTE("PENDIENTE"),
    COMPLETADO("COMPLETADO"),
    FALLIDO("FALLIDO"),
    REEMBOLSO("REEMBOLSO");

    private final String value;

    PagoEstado(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static PagoEstado fromValue(String text) {
        for (PagoEstado b : PagoEstado.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
