package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EvaluacionTipo {
    EXAMEN("EXAMEN"),
    TAREA("TAREA"),
    PROYECTO("PROYECTO"),
    QUIZ("QUIZ");

    private final String value;

    EvaluacionTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static EvaluacionTipo fromValue(String text) {
        for (EvaluacionTipo b : EvaluacionTipo.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
