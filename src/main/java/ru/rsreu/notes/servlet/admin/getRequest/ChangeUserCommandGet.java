package ru.rsreu.notes.servlet.admin.getRequest;

import ru.rsreu.notes.constant.RequestAttribute;
import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.service.UserService;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Jsp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserCommandGet extends FrontCommand {
    private UserService userService;

    public ChangeUserCommandGet(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        userService = ServiceFactory.getUserService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        String userIdString = request.getParameter(RequestConstants.USER_ID);
        if (userIdString != null) {
            Long userId = Long.valueOf(userIdString);
            User user = userService.getUser(userId);
            request.setAttribute(RequestAttribute.USER, user);
        }
        forward(Jsp.CHANGE_USER);
    }
}
