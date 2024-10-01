package ru.rsreu.notes.servlet.getRequest;

import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Jsp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommandGet extends FrontCommand {
    public LoginCommandGet(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
    }

    @Override
    public void execute() throws IOException {
        forward(Jsp.LOGIN);
    }
}
