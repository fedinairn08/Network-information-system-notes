package ru.rsreu.notes.service;

import lombok.RequiredArgsConstructor;
import ru.rsreu.notes.dataBase.DAOFactory;
import ru.rsreu.notes.dataBase.dao.NoteDAO;
import ru.rsreu.notes.entity.Note;
import ru.rsreu.notes.entity.enums.NoteStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class NoteService {
    private static NoteService instance;
    private final NoteDAO noteDAO;

    public static NoteService getInstance() {
        synchronized (NoteService.class) {
            if (instance == null) {
                instance = new NoteService(DAOFactory.getNoteDAO());
            }
        }
        return instance;
    }

    public Note saveNote(Note note) {
        return noteDAO.save(note);
    }

    public void hideNote(Long noteId) {
        Note note = this.getNote(noteId);
        this.updateNote(note.setNoteStatus(NoteStatus.HIDDEN));
    }

    public void unlockNote(Long noteId) {
        Note note = this.getNote(noteId);
        this.updateNote(note.setNoteStatus(NoteStatus.NOT_HIDDEN));
    }

    public void updateNote(Note note) {
        noteDAO.update(note);
    }

    public Note getNote(Long noteId) {
        return noteDAO.findNoteById(noteId);
    }

    public void deleteNote(Long noteId) {
        noteDAO.delete(noteId);
    }

    public void deleteNoteCategory(Long noteId) {
        noteDAO.deleteNoteCategory(noteId);
    }

    public List<Note> findAll() {return noteDAO.findAll();}

    public List<Note> findAllByUser(Long userId) {return noteDAO.findAllByToUser(userId);}

    public List<Note> findAllByCategory(Long categoryId) {return noteDAO.findAllByCategory(categoryId);}

    public void deleteUserNoteView(Long noteId) {noteDAO.deleteUserNoteView(noteId);}

    public void deleteNotesByUser(Long userId) {noteDAO.deleteNotesByUser(userId);}

    public void deleteUserNoteViewsByUser(Long userId) {noteDAO.deleteUserNoteViewsByUser(userId);}

    public void deleteNoteCategoriesByUser(Long userId) {noteDAO.deleteNoteCategoriesByUser(userId);}

    public boolean isNoteViewedByUser(Long noteId, Long userId) {return noteDAO.isNoteViewedByUser(noteId, userId);}

    public void saveUserNoteView(long userId, long noteId) {noteDAO.saveUserNoteView(userId, noteId);}

    public void deleteUserNoteViews(Long userId, Long noteId) {
        noteDAO.deleteUserNoteViews(userId, noteId);
    }

    public Map<Note, Boolean> getViewedNotes(Long userId, List<Note> notes) {
        Map<Note, Boolean> viewedNote = new HashMap<>();

        for (Note note : notes) {
            boolean isViewed = isNoteViewedByUser(userId, note.getNoteId());
            viewedNote.put(note, isViewed);
        }

        return viewedNote;
    }
}
