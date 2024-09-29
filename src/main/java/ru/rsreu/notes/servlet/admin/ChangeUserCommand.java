package ru.rsreu.notes.servlet.admin;

import ru.rsreu.notes.constant.RequestAttribute;
import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.entity.enums.Roles;
import ru.rsreu.notes.entity.enums.UserAuthorizationStatus;
import ru.rsreu.notes.entity.enums.UserBlockStatus;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.service.UserService;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Jsp;
import ru.rsreu.notes.utils.enums.Path;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUserCommand extends FrontCommand {
    private UserService userService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        userService = ServiceFactory.getUserService();
    }

    @Override
    public void process() throws ServletException, IOException {
        String userIdString = request.getParameter(RequestConstants.USER_ID);
        if (userIdString != null) {
            Long userId = Long.valueOf(userIdString);
            User user = userService.getUser(userId);
            request.setAttribute(RequestAttribute.USER, user);
        }
        forward(Jsp.CHANGE_USER);
    }

    @Override
    public void send() throws ServletException, IOException {
        User user;
        try {
            user = this.mapUser();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (user.getUserId() != null) {
            userService.updateUser(user);
        }

        response.sendRedirect(Path.USER_LIST.getAbsolutePath());
    }

    private User mapUser() {
        String id = request.getParameter("id");
        return new User(
                id != null && !id.isEmpty() ? Long.valueOf(request.getParameter("id")) : null,
                UserBlockStatus.NOT_BLOCKED,
                request.getParameter(RequestConstants.LOGIN),
                request.getParameter(RequestConstants.PASSWORD),
                request.getParameter(RequestConstants.FIRST_NAME),
                request.getParameter(RequestConstants.LAST_NAME),
                Roles.valueOf(request.getParameter(RequestConstants.ROLE)),
                UserAuthorizationStatus.NOT_AUTHORIZED
        );
    }
}
