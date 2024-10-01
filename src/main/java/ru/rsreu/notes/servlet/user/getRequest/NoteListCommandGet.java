package ru.rsreu.notes.servlet.user.getRequest;

import ru.rsreu.notes.constant.RequestAttribute;
import ru.rsreu.notes.entity.Note;
import ru.rsreu.notes.service.NoteService;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.utils.UserHelper;
import ru.rsreu.notes.utils.enums.Jsp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class NoteListCommandGet extends FrontCommand {
    private NoteService noteService;

    public NoteListCommandGet(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        noteService = ServiceFactory.getNoteService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        Optional<Long> userId = UserHelper.getUserFromCookies(request.getCookies());
        List<Note> notes = noteService.findAllByUser(userId.get());
        request.setAttribute(RequestAttribute.NOTES, notes);
        forward(Jsp.NOTE_LIST);
    }
}
