package com.skilling.lms.shared.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NotificacionTipo {
    ALERTA("ALERTA"),
    INFORMATIVA("INFORMATIVA"),
    PROMOCIONAL("PROMOCIONAL");

    private final String value;

    NotificacionTipo(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static NotificacionTipo fromValue(String text) {
        for (NotificacionTipo b : NotificacionTipo.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
