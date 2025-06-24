package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PlataformaVirtualTipo {
    LMS("LMS"),
    VIDEOCONFERENCIA("VIDEOCONFERENCIA"),
    GESTION_CONTENIDO("GESTION CONTENIDO");

    private final String value;

    PlataformaVirtualTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static PlataformaVirtualTipo fromValue(String text) {
        for (PlataformaVirtualTipo b : PlataformaVirtualTipo.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
