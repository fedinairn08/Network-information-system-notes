package ru.rsreu.notes.dataBase.dao;

import ru.rsreu.notes.entity.Category;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing Category entities.
 */
public interface CategoryDAO {
    /**
     * Retrieves all categories from the database.
     * @return A list of all categories.
     */
    List<Category> findAll();

    /**
     * Retrieves all categories that the user with the given ID is subscribed to.
     * @param id The ID of the user.
     * @return A list of categories the user is subscribed to.
     */
    List<Category> findAllSubscriptionsByUser(Long id);

    /**
     * Saves a new category to the database.
     * @param category The category to be saved.
     */
    void save(Category category);

    /**
     * Updates an existing category in the database.
     * @param category The category to be updated.
     */
    void updateCategory(Category category);

    /**
     * Finds a category by its ID.
     * @param id The ID of the category to find.
     * @return The category with the given ID, or null if not found.
     */
    Category findCategoryById(Long id);

    /**
     * Subscribes a user to a category.
     * @param userId The ID of the user subscribing to the category.
     * @param categoryId The ID of the category being subscribed to.
     */
    void subscribeToCategory(Long userId, Long categoryId);

    /**
     * Unsubscribes a user from a category.
     * @param userId The ID of the user unsubscribing from the category.
     * @param categoryId The ID of the category being unsubscribed from.
     */
    void unsubscribeFromCategory(Long userId, Long categoryId);

    /**
     * Checks if the user is subscribed to the specified category.
     * @param userId  The ID of the user whose subscription status is to be checked.
     * @param categoryId The ID of the category for which the subscription status is to be checked.
     * @return true if the user is subscribed to the category; false otherwise.
     */
    boolean isSubscribedToCategory(Long userId, Long categoryId);

    void categorySubscriptionDelete(Long userId);
}
