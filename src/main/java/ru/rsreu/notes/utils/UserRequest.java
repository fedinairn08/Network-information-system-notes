package ru.rsreu.notes.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import ru.rsreu.notes.entity.User;

import java.security.Principal;

public class UserRequest extends HttpServletRequestWrapper {
    private final User user;

    public UserRequest(HttpServletRequest request, User user) {
        super(request);
        this.user = user;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }
}
