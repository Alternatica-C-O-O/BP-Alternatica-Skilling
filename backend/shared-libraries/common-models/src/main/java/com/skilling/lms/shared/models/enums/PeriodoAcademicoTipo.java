package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PeriodoAcademicoTipo {
    ANUAL("ANUAL"),
    MENSUAL("MENSUAL"),
    SEMESTRE("SEMESTRAL"),
    CUATRIMESTRAL("CUATRIMESTRAL"),
    TRIMESTRAL("TRIMESTRAL"),
    BIMESTRAL("BIMESTRAL"),
    INTENSIVO("INTENSIVO"),
    VERANO("VERANO"),
    INVIERNO("INVIERNO");

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
