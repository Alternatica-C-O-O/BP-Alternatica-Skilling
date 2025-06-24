package com.skilling.lms.shared.models.enums;

public enum UserType {
    ESTUDIANTE("Estudiante"),
    DOCENTE("Docente"),
    ADMIN("Admin");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserType fromValue(String text) {
        for (UserType b : UserType.values()) {
            if (String.valueOf(b.value).equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + text + "'");
    }
}
