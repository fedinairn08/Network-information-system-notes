package ru.rsreu.notes.servlet;

import ru.rsreu.notes.utils.enums.Path;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HttpRequestClient {

    public abstract ICommand initCommand(String path, ServletContext servletContext, HttpServletRequest request, HttpServletResponse response);

    protected boolean isPath(String path, Path value) {
        return value.getRelativePath().equalsIgnoreCase(path.split("&")[0]);
    }
}
