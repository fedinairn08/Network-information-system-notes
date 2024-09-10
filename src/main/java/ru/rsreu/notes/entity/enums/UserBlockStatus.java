package ru.rsreu.notes.entity.enums;

public enum UserBlockStatus {
    BLOCKED("Заблокирован"),
    NOT_BLOCKED("Не заблокирован");

    private final String blockStatus;

    UserBlockStatus(String blockStatus) {
        this.blockStatus = blockStatus;
    }

    public String getRussianName() {
        return blockStatus;
    }
}
