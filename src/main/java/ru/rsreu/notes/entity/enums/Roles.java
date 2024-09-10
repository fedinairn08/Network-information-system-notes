package ru.rsreu.notes.entity.enums;

import java.util.List;

public enum Roles {
    ADMIN("Администратор"),
    MODERATOR("Модератор"),
    USER("Пользователь");

    private final String userRole;

    Roles(String role) {
        this.userRole = role;
    }

    public String getRussianName() {
        return userRole;
    }

    public static List<Roles> getAllRoles() {
        return List.of(ADMIN, MODERATOR, USER);
    }
}
