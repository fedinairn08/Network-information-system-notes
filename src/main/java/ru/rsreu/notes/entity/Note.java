package ru.rsreu.notes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.rsreu.notes.entity.enums.NoteStatus;

import java.sql.Date;
import java.util.List;

/**
 * Entity class representing a note.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Note {
    /**
     * Unique identifier for the note.
     */
    private Long noteId;
    /**
     * List of categories that the note belongs to.
     */
    private List<Category> categories;
    /**
     * Status of the note (hidden or not hidden).
     */
    private NoteStatus noteStatus;
    /**
     * Text content of the note.
     */
    private String text;
    /**
     * User who created the note.
     */
    private User user;
    /**
     * Date when the note was published.
     */
    private Date datePublication;
}
