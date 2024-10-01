package ru.rsreu.notes.servlet.user.postRequest;

import ru.rsreu.notes.constant.RequestConstants;
import ru.rsreu.notes.service.NoteService;
import ru.rsreu.notes.service.ServiceFactory;
import ru.rsreu.notes.servlet.FrontCommand;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteNoteCommandPost extends FrontCommand {
    private NoteService noteService;

    public DeleteNoteCommandPost(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super(servletContext, servletRequest, servletResponse);
        noteService = ServiceFactory.getNoteService();
    }

    @Override
    public void execute() throws IOException, ServletException {
        Long noteId = Long.valueOf(request.getParameter(RequestConstants.NOTE_ID));
        noteService.deleteUserNoteView(noteId);
        noteService.deleteNoteCategory(noteId);
        noteService.deleteNote(noteId);
    }
}
