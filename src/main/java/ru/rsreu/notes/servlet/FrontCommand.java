package ru.rsreu.notes.servlet;

import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.utils.UserHelper;
import ru.rsreu.notes.utils.enums.Jsp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand implements Command {

    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected User user;

    public FrontCommand(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
        try {
            this.user = UserHelper.tryGetFromRequest(request);
        } catch (Exception e) {
        }
    }

    @Override
    public abstract void execute() throws IOException, ServletException;

    protected void forward(Jsp page) {
        try {
            RequestDispatcher dispatcher = context.getRequestDispatcher(page.getPath());
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void redirect(String url) {
        try {
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
