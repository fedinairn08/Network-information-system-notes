package ru.rsreu.notes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * Entity class representing a user session.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Session {
    /**
     * Unique identifier for the session.
     */
    private Long sessionId;
    /**
     * User associated with the session.
     */
    private User user;
    /**
     * Timestamp indicating when the session expires.
     */
    private Timestamp activeUntil;

    /**
     * Constructor for creating a new session.
     * @param user The user associated with the session.
     * @param activeUntil The timestamp indicating when the session expires.
     */
    public Session(User user, Timestamp activeUntil) {
        this.user = user;
        this.activeUntil = activeUntil;
    }
}
