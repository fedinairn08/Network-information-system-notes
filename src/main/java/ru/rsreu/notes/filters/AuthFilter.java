package ru.rsreu.notes.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import ru.rsreu.notes.entity.Session;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.service.SessionService;
import ru.rsreu.notes.utils.SessionValidator;
import ru.rsreu.notes.utils.UserRequest;
import ru.rsreu.notes.utils.enums.Path;
import ru.rsreu.notes.utils.UserHelper;

public class AuthFilter implements Filter {
    private SessionService sessionService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        sessionService = ServiceFactory.getSessionService();
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getPathInfo();
        path = path.split("&")[0];

        if (path.equals(Path.NOT_FOUND.getRelativePath())) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<Long> userId = UserHelper.getUserFromCookies(request.getCookies());
        Optional<Session> session = userId.isPresent()
                ? sessionService.getSession(userId.get())
                : Optional.empty();

        if (!session.isPresent() || !SessionValidator.checkValid(session.get())) {
            if (path.equals(Path.LOGIN.getRelativePath())) {
                filterChain.doFilter(request, response);
                return;
            }

            response.sendRedirect(Path.LOGIN.getAbsolutePath());
            return;
        }

        HttpServletRequest wrapRequest = new UserRequest(request, session.get().getUser());

        filterChain.doFilter(wrapRequest, response);
    }
}
