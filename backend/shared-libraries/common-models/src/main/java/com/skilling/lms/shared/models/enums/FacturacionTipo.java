package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FacturacionTipo {
    MENSUAL("MENSUAL"),
    CURSO("CURSO"),
    ANUAL("ANUAL");

    private final String value;

    FacturacionTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static FacturacionTipo fromValue(String text) {
        for (FacturacionTipo b : FacturacionTipo.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
