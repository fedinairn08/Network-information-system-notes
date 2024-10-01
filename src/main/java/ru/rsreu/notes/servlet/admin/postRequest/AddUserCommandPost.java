package ru.rsreu.notes.servlet.admin.postRequest;

import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.entity.enums.Roles;
import ru.rsreu.notes.entity.enums.UserAuthorizationStatus;
import ru.rsreu.notes.entity.enums.UserBlockStatus;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.service.UserService;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Path;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserCommandPost extends FrontCommand {

    private UserService userService;

    public AddUserCommandPost(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        userService = ServiceFactory.getUserService();
    }

    @Override
    public void execute() throws IOException {
        User user;
        try {
            user = this.mapUser();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (user.getUserId() != null) {
            userService.updateUser(user);
        } else {
            userService.save(user);
        }

        response.sendRedirect(Path.ADMIN_PROFILE.getAbsolutePath());
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