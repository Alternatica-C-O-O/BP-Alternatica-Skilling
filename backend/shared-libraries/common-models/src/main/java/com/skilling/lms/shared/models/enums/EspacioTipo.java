package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EspacioTipo {
    Recepcion("Recepción"),
    Laboratorio("Laboratorio"),
    SalaDeComputo("Sala de Cómputo"),
    Gimnasio("Gimnasio"),
    SalaDeEstudio("Sala de estudio"),
    Vestibulo("Vestíbulo"),
    Cafeteria("Cafetería"),
    Consultorio("Consultorio"),
    Oficina("Oficina"),
    Auditorio("Auditorio");

    private final String value;

    EspacioTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static EspacioTipo fromValue(String text) {
        for (EspacioTipo b : EspacioTipo.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
