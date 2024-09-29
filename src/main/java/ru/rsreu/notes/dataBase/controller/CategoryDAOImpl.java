package ru.rsreu.notes.dataBase.controller;

import ru.rsreu.notes.dataBase.AbstractController;
import ru.rsreu.notes.dataBase.DataMapper;
import ru.rsreu.notes.dataBase.dao.CategoryDAO;
import ru.rsreu.notes.entity.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl extends AbstractController implements CategoryDAO {
    private static CategoryDAOImpl instance;

    public static CategoryDAOImpl getInstance() {
        synchronized (CategoryDAOImpl.class) {
            if (instance == null) {
                instance = new CategoryDAOImpl();
            }
        }
        return instance;
    }

    @Override
    public Category findCategoryById(Long id) {
        String query = resourcer.getString("category.find.id");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return DataMapper.toCategory(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void subscribeToCategory(Long userId, Long categoryId) {
        String query = resourcer.getString("category.subscription.save");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.setLong(2, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unsubscribeFromCategory(Long userId, Long categoryId) {
        String query = resourcer.getString("category.subscription.delete");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.setLong(2, categoryId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void categorySubscriptionDelete(Long userId) {
        String query = resourcer.getString("category.subscription.delete.by.user");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Category> findAll() {
        String query = resourcer.getString("category.find.all");
        List<Category> categories = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                categories.add(DataMapper.toCategory(resultSet));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public List<Category> findAllSubscriptionsByUser(Long id) {
        String query = resourcer.getString("category.subscriptions.find.all.by.id");
        List<Category> categories = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                categories.add(DataMapper.toCategory(resultSet));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public void save(Category category) {
        String query = resourcer.getString("category.save");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, category.getCategory());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(Category category) {
        String query = resourcer.getString("category.update");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, category.getCategoryId());
            statement.setString(2, category.getCategory());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isSubscribedToCategory(Long userId, Long categoryId) {
        String query = resourcer.getString("category.subscription.check");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.setLong(2, categoryId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
