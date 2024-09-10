package ru.rsreu.notes.servlet.moder;

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

public class AllNoteListCommand extends FrontCommand {
    private NoteService noteService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        noteService = ServiceFactory.getNoteService();
    }

    @Override
    public void process() throws ServletException, IOException {
        List<Note> notes = noteService.findAll();
        request.setAttribute(RequestAttribute.NOTES, notes);
        forward(Jsp.ALL_NOTE_LIST);
    }
}
