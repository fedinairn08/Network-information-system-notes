package ru.rsreu.notes.dataBase.dao;

import ru.rsreu.notes.entity.Note;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing Note entities.
 */
public interface NoteDAO {
    /**
     * Retrieves all notes from the database.
     * @return A list of all notes.
     */
    List<Note> findAll();

    /**
     * Retrieves all notes associated with a specific user.
     * @param toUserId The ID of the user whose notes are to be retrieved.
     * @return A list of notes associated with the given user.
     */
    List<Note> findAllByToUser(Long toUserId);

    /**
     * Updates an existing note in the database.
     * @param note The note to be updated.
     */
    void update(Note note);

    /**
     * Deletes a note from the database.
     * @param noteId The ID of the note to be deleted.
     */
    void delete(Long noteId);

    /**
     * Deletes all category associations for a given note.
     * @param noteId The ID of the note whose categories should be deleted.
     */
    void deleteNoteCategory(Long noteId);

    /**
     * Saves a new note to the database.
     * @param note The note to be saved.
     * @return The newly saved note with its assigned ID.
     */
    Note save(Note note);

    /**
     * Saves the association between a note and a category in the database.
     * @param note The note to be associated with the category.
     * @param noteId The ID of the note.
     * @param categoryId The ID of the category.
     */
    void saveNoteCategories(Note note, Long noteId, Long categoryId);

    /**
     * Finds a note by its ID.
     * @param id The ID of the note to find.
     * @return The note with the given ID, or null if not found.
     */
    Note findNoteById(Long id);
}
