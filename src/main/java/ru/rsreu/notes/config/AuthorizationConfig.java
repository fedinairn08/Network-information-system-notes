package ru.rsreu.notes.config;

import ru.rsreu.notes.entity.enums.Roles;
import ru.rsreu.notes.utils.enums.Path;
import static ru.rsreu.notes.utils.enums.Path.*;

import java.util.List;
import java.util.Map;

public class AuthorizationConfig {
    private static final Map<Roles, List<Path>> RolesRoutes = Map.ofEntries(
            Map.entry(Roles.ADMIN, List.of(
                    LOGIN,
                    LOGOUT,
                    ADMIN_PROFILE,
                    CHANGE_USER,
                    DELETE_USER,
                    USER_INFO,
                    ADD_USER,
                    USER_LIST
            )),
            Map.entry(Roles.MODERATOR, List.of(
                    LOGIN,
                    MODER_PROFILE,
                    ALL_NOTE_LIST,
                    ADD_CATEGORY,
                    LOGOUT,
                    BLOCK_USER,
                    UNBLOCK_USER,
                    HIDE_NOTE,
                    UNLOCK_NOTE,
                    USER_LIST
            )),
            Map.entry(Roles.USER, List.of(
                    LOGIN,
                    LOGOUT,
                    ADD_NOTE,
                    NOTE_LIST,
                    NOTE_LIST_BY_CATEGORY,
                    DELETE_NOTE,
                    CATEGORY_LIST,
                    CATEGORY_SUBSCRIPTIONS_LIST,
                    USER_SUBSCRIPTIONS_LIST,
                    ONLY_USER_LIST,
                    SUBSCRIBE_TO_CATEGORY,
                    SUBSCRIBE_TO_USER,
                    UNSUBSCRIBE_FROM_CATEGORY,
                    UNSUBSCRIBE_FROM_USER,
                    VIEWED_NOTE,
                    NOT_VIEWED_NOTE
            ))
    );

    private static final Map<Roles, Path> RolesStartPage = Map.ofEntries(
            Map.entry(Roles.MODERATOR, Path.MODER_PROFILE),
            Map.entry(Roles.ADMIN, Path.ADMIN_PROFILE),
            Map.entry(Roles.USER, Path.USER_PROFILE)
    );

    public static List<Path> getRoutes(Roles Roles) {
        return RolesRoutes.get(Roles);
    }

    public static Path getStartPage(Roles Roles) {
        return RolesStartPage.get(Roles);
    }
}
