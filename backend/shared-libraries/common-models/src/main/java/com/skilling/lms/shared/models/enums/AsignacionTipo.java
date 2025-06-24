package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AsignacionTipo {
    CLASE("CLASE"),
    CONSULTA("CONSULTA"),
    LABORATORIO("LABORATORIO");

    private final String value;

    AsignacionTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static AsignacionTipo fromValue(String text) {
        for (AsignacionTipo b : AsignacionTipo.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
