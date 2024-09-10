package ru.rsreu.notes.servlet.admin;

import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.service.SessionService;
import ru.rsreu.notes.service.UserService;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Path;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserCommand extends FrontCommand {

    private UserService userService;
    private SessionService sessionService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        userService = ServiceFactory.getUserService();
        sessionService = ServiceFactory.getSessionService();
    }

    @Override
    public void send() throws ServletException, IOException {
        Long userId = Long.valueOf(request.getParameter(RequestConstants.USER_ID));
        sessionService.deleteSession(userId);
        userService.deleteUser(userId);
        redirect(Path.ADMIN_PROFILE.getAbsolutePath());
    }
}
