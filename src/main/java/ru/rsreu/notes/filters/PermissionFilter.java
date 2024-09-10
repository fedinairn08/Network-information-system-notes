package ru.rsreu.notes.filters;

import ru.rsreu.notes.config.AuthorizationConfig;
import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.utils.UserHelper;
import ru.rsreu.notes.utils.enums.Path;
import ru.rsreu.notes.utils.PermissionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class PermissionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getPathInfo();

        if (path.equals(Path.NOT_FOUND.getRelativePath())) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<User> user = UserHelper.getFromRequest(request);

        if (!user.isPresent()) {
            if (path.equals(Path.LOGIN.getRelativePath())) {
                filterChain.doFilter(request, response);
                return;
            }

            response.sendRedirect(Path.LOGIN.getAbsolutePath());
            return;
        }

        if (PermissionUtils.hasPermission(path, user.get())) {
            filterChain.doFilter(request, response);
            return;
        }

        Path startRoute = AuthorizationConfig.getStartPage(user.get().getUserRole());
        response.sendRedirect(startRoute.getAbsolutePath());
    }
}
