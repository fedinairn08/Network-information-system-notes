package ru.rsreu.notes.servlet.user.getRequest;

import ru.rsreu.notes.constant.RequestAttribute;
import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.entity.Category;
import ru.rsreu.notes.entity.Note;
import ru.rsreu.notes.service.CategoryService;
import ru.rsreu.notes.service.NoteService;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.enums.Jsp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddNoteCommandGet extends FrontCommand {
    private NoteService noteService;
    private CategoryService categoryService;

    public AddNoteCommandGet(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        noteService = ServiceFactory.getNoteService();
        categoryService = ServiceFactory.getCategoryService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        String noteIdString = request.getParameter(RequestConstants.NOTE_ID);
        List<Category> categories = categoryService.findAll();
        request.setAttribute(RequestAttribute.CATEGORIES, categories);

        if (noteIdString != null) {
            Long noteId = Long.valueOf(noteIdString);
            Note note = noteService.getNote(noteId);
            request.setAttribute(RequestAttribute.NOTE, note);
        }
        forward(Jsp.ADD_NOTE);
    }
}
