package ru.rsreu.notes.servlet.getRequest;

import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Jsp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmptyCommandGet extends FrontCommand {
    public EmptyCommandGet(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
    }

    @Override
    public void execute() throws IOException, ServletException {
        forward(Jsp.LOGIN);
    }
}
