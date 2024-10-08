package ru.rsreu.notes.servlet.user.getRequest;

import ru.rsreu.notes.constant.RequestAttribute;
import ru.rsreu.notes.dto.UserResponseDTO;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.service.SessionService;
import ru.rsreu.notes.service.UserService;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Jsp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnlyUserListCommandGet extends FrontCommand {
    private SessionService sessionService;
    private UserService userService;

    public OnlyUserListCommandGet(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        sessionService = ServiceFactory.getSessionService();
        userService = ServiceFactory.getUserService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        List<UserResponseDTO> sessions = sessionService.getAllUserList(user);
        Long currentUserId = user.getUserId();

        Map<Long, Boolean> userSubscriptionStatus = new HashMap<>();
        for (UserResponseDTO session : sessions) {
            boolean isSubscribed = userService.isSubscribedToUser(currentUserId, session.getUser().getUserId());
            userSubscriptionStatus.put(session.getUser().getUserId(), isSubscribed);
        }

        request.setAttribute("userSubscriptionStatus", userSubscriptionStatus);
        request.setAttribute(RequestAttribute.ROLE, user.getUserRole());
        request.setAttribute(RequestAttribute.SESSIONS, sessions);
        forward(Jsp.ONLY_USER_LIST);
    }
}
