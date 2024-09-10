package ru.rsreu.notes.servlet.user;

import ru.rsreu.notes.constant.RequestAttribute;
import ru.rsreu.notes.dto.UserResponseDTO;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.service.SessionService;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Jsp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OnlyUserListCommand extends FrontCommand {
    private SessionService sessionService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        sessionService = ServiceFactory.getSessionService();
    }

    @Override
    public void process() throws ServletException, IOException {
        List<UserResponseDTO> sessions = sessionService.getAllUserList(user);
        request.setAttribute(RequestAttribute.ROLE, user.getUserRole());
        request.setAttribute(RequestAttribute.SESSIONS, sessions);
        forward(Jsp.ONLY_USER_LIST);
    }
}
