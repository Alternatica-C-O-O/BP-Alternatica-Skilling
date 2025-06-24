package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum InscripcionEstado {
    ACTIVO("ACTIVO"),
    COMPLETADO("COMPLETADO"),
    ABANDONADO("ABANDONADO");

    private final String value;

    InscripcionEstado(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static InscripcionEstado fromValue(String text) {
        for (InscripcionEstado b : InscripcionEstado.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
