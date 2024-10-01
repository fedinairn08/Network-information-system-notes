package ru.rsreu.notes.servlet;

import javax.servlet.ServletException;
import java.io.IOException;

public interface CommandInvoker {
    void invokeCommand() throws ServletException, IOException;
}
