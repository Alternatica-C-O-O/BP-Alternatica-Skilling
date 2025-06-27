package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EventoTipo {
    LOGIN("LOGIN"),
    COURSE_ACCESS("COURSE ACCESS"),
    GRADE_UPDATE("GRADE UPDATE");

    private final String value;

    EventoTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static EventoTipo fromValue(String text) {
        for (EventoTipo b : EventoTipo.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
