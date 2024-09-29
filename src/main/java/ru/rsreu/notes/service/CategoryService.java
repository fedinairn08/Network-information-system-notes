package ru.rsreu.notes.service;

import lombok.RequiredArgsConstructor;
import ru.rsreu.notes.dataBase.DAOFactory;
import ru.rsreu.notes.dataBase.dao.CategoryDAO;
import ru.rsreu.notes.entity.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class CategoryService {
    private static CategoryService instance;
    private final CategoryDAO categoryDAO;

    public static CategoryService getInstance() {
        synchronized (CategoryService.class) {
            if (instance == null) {
                instance = new CategoryService(DAOFactory.getCategoryDAO());
            }
        }
        return instance;
    }

    public Category getCategory(Long id) {
        return categoryDAO.findCategoryById(id);
    }

    public void save(Category category) {
        categoryDAO.save(category);
    }
    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    public List<Category> findAll() {return categoryDAO.findAll();}

    public List<Category> findAllSubscriptionsByUser(Long id) {return categoryDAO.findAllSubscriptionsByUser(id);}

    public void subscribeToCategory(Long userId, Long categoryId) {
        categoryDAO.subscribeToCategory(userId, categoryId);
    }

    public void unsubscribeFromCategory(Long userId, Long categoryId) {
        categoryDAO.unsubscribeFromCategory(userId, categoryId);
    }

    public void categorySubscriptionDelete(Long userId) {
        categoryDAO.categorySubscriptionDelete(userId);
    }

    public boolean isUserSubscribedToCategory(Long userId, Long categoryId) {
        return categoryDAO.isSubscribedToCategory(userId, categoryId);
    }

    public Map<Category, Boolean> getCategoriesWithSubscriptionStatus(Long userId) {
        List<Category> categories = categoryDAO.findAll();
        Map<Category, Boolean> categorySubscriptionStatus = new HashMap<>();

        for (Category category : categories) {
            boolean isSubscribed = isUserSubscribedToCategory(userId, category.getCategoryId());
            categorySubscriptionStatus.put(category, isSubscribed);
        }

        return categorySubscriptionStatus;
    }
}
