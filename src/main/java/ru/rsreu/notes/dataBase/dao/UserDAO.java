package ru.rsreu.notes.dataBase.dao;

import java.util.List;
import java.util.Optional;

import ru.rsreu.notes.entity.User;

/**
 * Data Access Object (DAO) interface for managing User entities.
 */
public interface UserDAO {
    /**
     * Retrieves all users from the database.
     * @return A list of all users.
     */
    List<User> findAllUsers();

    /**
     * Finds a user by their login.
     * @param login The login name of the user to find.
     * @return The user with the given login, or null if not found.
     */
    User findUserByLogin(String login);

    /**
     * Finds a user by their ID.
     * @param id The ID of the user to find.
     * @return The user with the given ID, or null if not found.
     */
    User findUserById(Long id);

    /**
     * Updates an existing user in the database.
     * @param user The user to be updated.
     * @return An optional containing the updated user, or empty if the update failed.
     */
    Optional<User> update(User user);

    /**
     * Deletes a user from the database.
     * @param userId The ID of the user to be deleted.
     */
    void delete(Long userId);

    /**
     * Subscribes a user to another user.
     * @param userId The ID of the user subscribing.
     * @param subscriptionUserId The ID of the user being subscribed to.
     */
    void subscribeToUser(Long userId, Long subscriptionUserId);

    /**
     * Saves a new user to the database.
     * @param user The user to be saved.
     */
    void save(User user);

    /**
     * Retrieves all users that the user with the given ID is subscribed to.
     * @param id The ID of the user.
     * @return A list of users the user is subscribed to.
     */
    List<User> findAllSubscriptionsByUser(Long id);

    /**
     * Unsubscribes a user from another user.
     * @param userId The ID of the user unsubscribing.
     * @param subscriptionUserId The ID of the user being unsubscribed from.
     */
    void unsubscribeFromUser(Long userId, Long subscriptionUserId);
}
