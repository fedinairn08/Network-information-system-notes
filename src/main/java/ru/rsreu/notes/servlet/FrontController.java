package ru.rsreu.notes.servlet;

import ru.rsreu.notes.config.PathMappingConfig;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FrontController servlet.
 * This servlet acts as a central point for handling incoming requests.
 */
@WebServlet("/FrontControllerServlet")
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public FrontController() {
        super();
    }

    /**
     * Handles GET requests.
     * @param request The HTTP request object.
     * @param response The HTTP response object.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        FrontCommand command = PathMappingConfig.getCommand(request.getPathInfo());

        try {
            command.init(getServletContext(), request, response);
            command.process();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Handles POST requests.
     * @param request The HTTP request object.
     * @param response The HTTP response object.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        FrontCommand command = PathMappingConfig.getCommand(request.getPathInfo());

        try {
            command.init(getServletContext(), request, response);
            command.send();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}