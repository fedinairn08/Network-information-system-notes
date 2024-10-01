package ru.rsreu.notes.servlet.user.postRequest;

import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.entity.Category;
import ru.rsreu.notes.entity.Note;
import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.entity.enums.NoteStatus;
import ru.rsreu.notes.service.CategoryService;
import ru.rsreu.notes.service.NoteService;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.UserHelper;
import ru.rsreu.notes.utils.enums.Path;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AddNoteCommandPost extends FrontCommand {
    private NoteService noteService;
    private CategoryService categoryService;

    public AddNoteCommandPost(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        noteService = ServiceFactory.getNoteService();
        categoryService = ServiceFactory.getCategoryService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        try {
            Note note = mapNote();
            User user = UserHelper.getFromRequest(request).get();
            note.setUser(user);
            noteService.saveNote(note);

            response.sendRedirect(Path.USER_PROFILE.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Note mapNote() {
        String id = request.getParameter("id");
        Date datePublication = new java.sql.Date(System.currentTimeMillis());
        String[] categoryIdsParam = request.getParameterValues(RequestConstants.NOTE_CATEGORY);
        if (categoryIdsParam == null || categoryIdsParam.length == 0) {
            throw new IllegalArgumentException("Категория заметки не выбрана");
        }

        List<Category> categories = new ArrayList<>();
        for (String categoryIdStr : categoryIdsParam) {
            Long categoryId = Long.valueOf(categoryIdStr);
            Category category = categoryService.getCategory(categoryId);
            categories.add(category);
        }

        return new Note(
                id != null && !id.isEmpty() ? Long.valueOf(request.getParameter("id")) : null,
                categories,
                NoteStatus.NOT_HIDDEN,
                request.getParameter(RequestConstants.TEXT),
                null,
                datePublication
        );
    }
}
