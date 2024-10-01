package ru.rsreu.notes.servlet.getRequest;

import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.service.SessionService;
import ru.rsreu.notes.service.UserService;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.UserHelper;
import ru.rsreu.notes.utils.enums.Path;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LogoutCommandGet extends FrontCommand {
    private SessionService sessionService;
    private UserService userService;

    public LogoutCommandGet(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        sessionService = ServiceFactory.getSessionService();
        userService = ServiceFactory.getUserService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.invalidate();
        Optional<Long> userId = UserHelper.getUserFromCookies(request.getCookies());
        userId.ifPresent(longId -> {
            sessionService.deleteSession(longId);
            userService.updateUserStatusNotAuthorized(longId);
        });
        redirect(Path.LOGIN.getAbsolutePath());
    }
}
