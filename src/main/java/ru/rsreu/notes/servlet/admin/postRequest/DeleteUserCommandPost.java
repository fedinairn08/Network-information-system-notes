package ru.rsreu.notes.servlet.admin.postRequest;

import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.service.*;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Path;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserCommandPost extends FrontCommand {
    private UserService userService;
    private SessionService sessionService;
    private NoteService noteService;
    private CategoryService categoryService;

    public DeleteUserCommandPost(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        userService = ServiceFactory.getUserService();
        sessionService = ServiceFactory.getSessionService();
        noteService = ServiceFactory.getNoteService();
        categoryService = ServiceFactory.getCategoryService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        Long userId = Long.valueOf(request.getParameter(RequestConstants.USER_ID));
        sessionService.deleteSession(userId);
        noteService.deleteNoteCategoriesByUser(userId);
        userService.deleteUserNoteView(userId);
        userService.userSubscriptionDelete(userId);
        categoryService.categorySubscriptionDelete(userId);
        noteService.deleteNotesByUser(userId);
        userService.deleteUser(userId);
        redirect(Path.ADMIN_PROFILE.getAbsolutePath());
    }
}
