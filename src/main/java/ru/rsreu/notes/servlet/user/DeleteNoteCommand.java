package ru.rsreu.notes.servlet.user;

import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.service.NoteService;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.servlet.FrontCommand;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteNoteCommand extends FrontCommand {
    private NoteService noteService;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);
        noteService = ServiceFactory.getNoteService();
    }

    @Override
    public void send() throws ServletException, IOException {
        Long noteId = Long.valueOf(request.getParameter(RequestConstants.NOTE_ID));
        noteService.deleteNoteCategory(noteId);
        noteService.deleteNote(noteId);
    }
}
