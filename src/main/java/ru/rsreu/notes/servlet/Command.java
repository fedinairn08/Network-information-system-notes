package ru.rsreu.notes.servlet;

import javax.servlet.ServletException;
import java.io.IOException;

public interface Command {
    void execute() throws IOException, ServletException;
}
