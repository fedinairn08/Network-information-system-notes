package ru.rsreu.notes.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import ru.rsreu.notes.entity.User;

import java.util.Optional;

public class UserHelper {
    private static final int COOKIE_MAX_AGE = 3600; //час
    private static final String USER_ID = "userId";

    private UserHelper(){

    }

    public static Cookie generateUserCookie(User user) {
        Cookie cookie = new Cookie(USER_ID, user.getUserId().toString());
        cookie.setMaxAge(COOKIE_MAX_AGE);

        return cookie;
    }

    public static Optional<User> getFromRequest(HttpServletRequest request) {
        User user = (User)request.getUserPrincipal();

        return Optional.ofNullable(user);
    }

    public static User tryGetFromRequest(HttpServletRequest request) {
        return getFromRequest(request).orElseThrow(RuntimeException::new);
    }

    public static Optional<Long> getUserFromCookies(Cookie[] cookies) {
        if (cookies == null) {
            return Optional.empty();
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(USER_ID)) {
                String value = cookie.getValue();

                return value == null ? Optional.empty() : Optional.of(Long.parseLong(value));
            }
        }

        return Optional.empty();
    }
}
