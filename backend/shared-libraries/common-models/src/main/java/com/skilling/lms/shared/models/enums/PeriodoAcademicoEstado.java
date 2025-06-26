package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PeriodoAcademicoEstado {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    FUTURO("FUTURO"),
    PASADO("PASADO"),
    PLANIFICADO("PLANIFICADO"),
    EN_CURSO("EN CURSO"),
    CANCELADO("CANCELADO"),
    PENDIENTE("PENDIENTE"),
    FINALIZADO("FINALIZADO");

    private final String value;

    PeriodoAcademicoEstado(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static PeriodoAcademicoEstado fromValue(String text) {
        for (PeriodoAcademicoEstado b : PeriodoAcademicoEstado.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
