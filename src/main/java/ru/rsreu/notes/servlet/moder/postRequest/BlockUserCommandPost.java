package ru.rsreu.notes.servlet.moder.postRequest;

import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.service.UserService;
import ru.rsreu.notes.servlet.FrontCommand;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockUserCommandPost extends FrontCommand {
    private UserService userService;

    public BlockUserCommandPost(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        userService = ServiceFactory.getUserService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        Long userId = Long.valueOf(request.getParameter(RequestConstants.USER_ID));
        userService.blockUser(userId);
    }
}
