package ru.rsreu.notes.servlet;

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
    private HttpRequestClient getClient;
    private HttpRequestClient postClient;

    /**
     * Default constructor.
     */
    public FrontController() {
        super();
        getClient = new GetRequestClient();
        postClient = new PostRequestClient();
    }

    /**
     * Handles GET requests.
     * @param request The HTTP request object.
     * @param response The HTTP response object.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ICommand command = getClient.initCommand(request.getPathInfo(), getServletContext(), request, response);
        CommandInvoker commandInvoker = new HttpRequestInvoker(command);

        try {
            commandInvoker.invokeCommand();
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
        ICommand command = postClient.initCommand(request.getPathInfo(), getServletContext(), request, response);
        CommandInvoker commandInvoker = new HttpRequestInvoker(command);

        try {
            commandInvoker.invokeCommand();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}