package ru.rsreu.notes.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.rsreu.notes.config.AuthorizationConfig;
import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.entity.enums.Roles;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.service.SessionService;
import ru.rsreu.notes.service.UserService;
import ru.rsreu.notes.utils.UserHelper;
import ru.rsreu.notes.utils.enums.Jsp;
import ru.rsreu.notes.utils.enums.Path;
import ru.rsreu.notes.exceptions.UserBlockedException;

import java.io.IOException;

public class LoginCommand extends FrontCommand{
    private SessionService sessionService;
    private UserService userService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        sessionService = ServiceFactory.getSessionService();
        userService = ServiceFactory.getUserService();
    }
    @Override
    public void process() throws ServletException, IOException {
        forward(Jsp.LOGIN);
    }

    @Override
    public void send() throws ServletException, IOException {
        String username = request.getParameter(RequestConstants.LOGIN);
        String password = request.getParameter(RequestConstants.PASSWORD);

        try {
            User user = sessionService.createSession(username, password);
            Roles role = user.getUserRole();
            Path startRoute = AuthorizationConfig.getStartPage(role);
            Cookie userCookie = UserHelper.generateUserCookie(user);
            userService.updateUserStatusAuthorized(user.getUserId());
            response.addCookie(userCookie);
            response.sendRedirect(startRoute.getAbsolutePath());
        } catch (UserBlockedException e) {
            request.setAttribute("errorMessage", "Ваш аккаунт заблокирован");
            request.getRequestDispatcher("/jsp/authorization.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(Path.LOGIN.getAbsolutePath());
        }
    }
}
