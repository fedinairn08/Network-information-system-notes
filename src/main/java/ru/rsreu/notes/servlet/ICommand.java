package ru.rsreu.notes.servlet;

import javax.servlet.ServletException;
import java.io.IOException;

public interface ICommand {
    void execute() throws IOException, ServletException;
}
