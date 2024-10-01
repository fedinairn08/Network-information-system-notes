package ru.rsreu.notes.servlet.moder.getRequest;

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

public class ModerProfileCommandGet extends FrontCommand {
    private SessionService sessionService;

    public ModerProfileCommandGet(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        sessionService = ServiceFactory.getSessionService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        List<UserResponseDTO> sessions = sessionService.getAllUserList(user);
        request.setAttribute(RequestAttribute.ROLE, user.getUserRole());
        request.setAttribute(RequestAttribute.SESSIONS, sessions);
        forward(Jsp.MODER_PROFILE);
    }
}
