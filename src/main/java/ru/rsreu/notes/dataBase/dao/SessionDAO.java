package ru.rsreu.notes.dataBase.dao;

import ru.rsreu.notes.entity.Session;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object (DAO) interface for managing Session entities.
 */
public interface SessionDAO {
    /**
     * Saves a new session to the database.
     * @param session The session to be saved.
     */
    void save(Session session);

    /**
     * Retrieves all sessions from the database.
     * @return A list of all sessions.
     */
    List<Session> findAll();

    /**
     * Deletes all sessions associated with a specific user.
     * @param userId The ID of the user whose sessions should be deleted.
     */
    void delete(Long userId);

    /**
     * Finds a session by its associated user ID.
     * @param userId The ID of the user whose session is to be found.
     * @return An optional containing the session if found, or empty if no session is found.
     */
    Optional<Session> findByUserId(Long userId);

    /**
     * Updates an existing session in the database.
     * @param session The session to be updated.
     */
    void update(Session session);
}
