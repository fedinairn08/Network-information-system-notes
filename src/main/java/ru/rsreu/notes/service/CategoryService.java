package ru.rsreu.notes.service;

import lombok.RequiredArgsConstructor;
import ru.rsreu.notes.dataBase.DAOFactory;
import ru.rsreu.notes.dataBase.dao.CategoryDAO;
import ru.rsreu.notes.entity.Category;

import java.util.List;

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

    public void subscribeToCategory(Long userId, Long categoryId){
        categoryDAO.subscribeToCategory(userId, categoryId);
    }

    public void unsubscribeFromCategory(Long userId, Long categoryId){
        categoryDAO.unsubscribeFromCategory(userId, categoryId);
    }
}
