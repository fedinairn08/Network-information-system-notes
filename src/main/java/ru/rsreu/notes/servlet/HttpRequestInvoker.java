package ru.rsreu.notes.servlet;

import javax.servlet.ServletException;
import java.io.IOException;

public class HttpRequestInvoker implements CommandInvoker {
    private Command command;

    public HttpRequestInvoker(Command command) {
        this.command = command;
    }

    @Override
    public void invokeCommand() throws ServletException, IOException {
        command.execute();
    }
}
