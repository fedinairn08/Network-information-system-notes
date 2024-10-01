package ru.rsreu.notes.servlet.moder.getRequest;

import ru.rsreu.notes.constant.RequestAttribute;
import ru.rsreu.notes.entity.Note;
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

public class AllNoteListCommandGet extends FrontCommand {
    private NoteService noteService;

    public AllNoteListCommandGet(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        noteService = ServiceFactory.getNoteService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        List<Note> notes = noteService.findAll();
        request.setAttribute(RequestAttribute.NOTES, notes);
        forward(Jsp.ALL_NOTE_LIST);
    }
}
