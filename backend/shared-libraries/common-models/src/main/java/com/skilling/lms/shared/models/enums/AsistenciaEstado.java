package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AsistenciaEstado {
    PRESENTE("PRESENTE"),
    AUSENTE("AUSENTE"),
    JUSTIFICADO("JUSTIFICADO"),
    TARDE("TARDE");

    private final String value;

    AsistenciaEstado(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static AsistenciaEstado fromValue(String text) {
        for (AsistenciaEstado b : AsistenciaEstado.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
