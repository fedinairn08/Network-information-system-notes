package ru.rsreu.notes.listeners;

import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.service.SessionService;
import ru.rsreu.notes.service.UserService;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionTimeoutListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        User user = (User) event.getSession().getAttribute("user");

        if (user != null) {
            UserService userService = ServiceFactory.getUserService();
            SessionService sessionService = ServiceFactory.getSessionService();

            sessionService.deleteSession(user.getUserId());
            userService.updateUserStatusNotAuthorized(user.getUserId());
        }
    }
}
