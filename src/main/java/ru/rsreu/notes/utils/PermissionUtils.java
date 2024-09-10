package ru.rsreu.notes.utils;

import ru.rsreu.notes.config.AuthorizationConfig;
import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.utils.enums.Path;

import java.util.List;

public class PermissionUtils {
    public static boolean hasPermission(String path, User user) {
        List<Path> routes = AuthorizationConfig.getRoutes(user.getUserRole());

        for (Path route : routes) {
            if (path.contains(route.getRelativePath())) {
                return true;
            }
        }

        return false;
    }
}
