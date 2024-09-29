package ru.rsreu.notes.entity.enums;

public enum UserAuthorizationStatus {
    AUTHORIZED("Авторизован"),
    NOT_AUTHORIZED("Не авторизован");

    private final String authorizationStatus;

    UserAuthorizationStatus(String authorizationStatus) {
        this.authorizationStatus = authorizationStatus;
    }

    public String getRussianName() {
        return authorizationStatus;
    }
}
