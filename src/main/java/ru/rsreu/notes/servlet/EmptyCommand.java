package ru.rsreu.notes.servlet;

import javax.servlet.ServletException;
import ru.rsreu.notes.utils.enums.Jsp;

import java.io.IOException;

public class EmptyCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        forward(Jsp.LOGIN);
    }

    @Override
    public void send() throws ServletException, IOException {

    }
}
