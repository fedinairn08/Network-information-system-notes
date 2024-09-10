package ru.rsreu.notes.servlet.user;

import ru.rsreu.notes.constant.RequestAttribute;
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
import java.util.List;

public class UserSubscriptionsListCommand extends FrontCommand {
    private UserService userService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        userService = ServiceFactory.getUserService();
    }

    @Override
    public void process() throws ServletException, IOException {
        List<User> users = userService.findAllSubscriptionsByUser(user.getUserId());
        request.setAttribute(RequestAttribute.USERS, users);
        forward(Jsp.USER_SUBSCRIPTIONS_LIST);
    }
}
