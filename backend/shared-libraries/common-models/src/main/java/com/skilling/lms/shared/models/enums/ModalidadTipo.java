package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ModalidadTipo {
    VIRTUAL("VIRTUAL"),
    PRESENCIAL("PRESENCIAL"),
    SEMIPRESENCIAL("SEMIPRESENCIAL");

    private final String value;

    ModalidadTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ModalidadTipo fromValue(String text) {
        for (ModalidadTipo b : ModalidadTipo.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
