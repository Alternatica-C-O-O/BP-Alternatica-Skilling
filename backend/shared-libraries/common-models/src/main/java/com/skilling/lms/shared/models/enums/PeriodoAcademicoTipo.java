package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PeriodoAcademicoTipo {
    SEMESTRE("SEMESTRE"),
    CUATRIMESTRE("CUATRIMESTRE"),
    TRIMESTRE("TRIMESTRE"),
    ANUAL("ANUAL"),
    VERANO("VERANO"),
    INTENSIVO("INTENSIVO");

    private final String value;

    PeriodoAcademicoTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static PeriodoAcademicoTipo fromValue(String text) {
        for (PeriodoAcademicoTipo b : PeriodoAcademicoTipo.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}

