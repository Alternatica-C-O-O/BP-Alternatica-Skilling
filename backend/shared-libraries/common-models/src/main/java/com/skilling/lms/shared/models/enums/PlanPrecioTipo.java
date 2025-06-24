package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PlanPrecioTipo {
    MENSUAL("MENSUAL"),
    CURSO("CURSO"),
    ANUAL("ANUAL");

    private final String value;

    PlanPrecioTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static PlanPrecioTipo fromValue(String text) {
        for (PlanPrecioTipo b : PlanPrecioTipo.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
