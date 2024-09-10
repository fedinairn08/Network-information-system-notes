package ru.rsreu.notes.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.utils.UserHelper;
import ru.rsreu.notes.utils.enums.Jsp;

import java.io.IOException;

public abstract class FrontCommand {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected User user;

    public void init(
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

    public void process() throws ServletException, IOException{

    }

    public void send() throws ServletException, IOException{

    }

    protected void forward(Jsp page) throws ServletException, IOException {
        RequestDispatcher dispatcher = context.getRequestDispatcher(page.getPath());
        dispatcher.forward(request, response);
    }

    protected void redirect(String url) throws IOException {
        response.sendRedirect(url);
    }
}
