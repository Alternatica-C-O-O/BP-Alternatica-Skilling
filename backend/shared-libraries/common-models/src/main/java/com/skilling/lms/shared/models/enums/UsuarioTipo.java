package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UsuarioTipo {
    ESTUDIANTE("ESTUDIANTE"),
    PROFESOR("PROFESOR"),
    ADMINISTRADOR("ADMINISTRADOR"),
    INVITADO("INVITADO");

    private final String value;

    UsuarioTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static UsuarioTipo fromValue(String text) {
        for (UsuarioTipo b : UsuarioTipo.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
