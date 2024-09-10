package ru.rsreu.notes.servlet.user;

import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.service.UserService;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Jsp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubscribeUserCommand extends FrontCommand {
    private UserService userService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        userService = ServiceFactory.getUserService();
    }

    @Override
    public void send() throws ServletException, IOException {
        Long subscriptionUserId = Long.valueOf(request.getParameter(RequestConstants.SUBSCRIPTION_USER_ID));
        userService.subscribeToUser(user.getUserId(), subscriptionUserId);
        forward(Jsp.ONLY_USER_LIST);
    }
}
