package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ArchivoTipo {
    PDF("PDF"),
    VIDEO("VIDEO"),
    PPT("PPT"),
    SCORM("SCORM");

    private final String value;

    ArchivoTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ArchivoTipo fromValue(String text) {
        for (ArchivoTipo b : ArchivoTipo.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
