package ru.rsreu.notes.entity.enums;

public enum SessionStatus {
    AUTHORIZED("Авторизован"),
    NOT_AUTHORIZED( "Не авторизован");
    private final String name;

    SessionStatus(String name) {
        this.name = name;
    }

    public String getRussianName() {
        return name;
    }
}
