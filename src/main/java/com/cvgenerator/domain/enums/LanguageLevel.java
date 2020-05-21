package com.cvgenerator.domain.enums;

public enum LanguageLevel {

    A1("A1"),
    A2("A2"),
    B1("B1"),
    B2("B2"),
    C1("C1"),
    C2("C2"),
    NATIVE("Native");

    private String value;

    LanguageLevel(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
