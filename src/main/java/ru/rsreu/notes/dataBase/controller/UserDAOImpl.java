package ru.rsreu.notes.dataBase.controller;

import ru.rsreu.notes.dataBase.AbstractController;
import ru.rsreu.notes.dataBase.dao.UserDAO;
import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.dataBase.DataMapper;
import ru.rsreu.notes.resourcer.ProjectResourcer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends AbstractController implements UserDAO {
    private static UserDAOImpl instance;

    public static UserDAOImpl getInstance() {
        synchronized (UserDAOImpl.class) {
            if (instance == null) {
                instance = new UserDAOImpl();
            }
        }
        return instance;
    }

    @Override
    public List<User> findAllUsers() {
        String query = resourcer.getString("user.find.all");
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(DataMapper.toUser(resultSet));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findUserByLogin(String login) {
        String query = ProjectResourcer.getInstance().getString("user.find.login");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return DataMapper.toUser(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findUserById(Long id) {
        String query = resourcer.getString("user.find.id");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return DataMapper.toUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<User> update(User user) {
        String query = resourcer.getString("user.update");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUserBlockStatus().toString());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getUserRole().toString());
            statement.setString(7, user.getUserAuthorizationStatus().toString());
            statement.setLong(8, user.getUserId());

            statement.executeUpdate();

            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long userId) {
        String query = resourcer.getString("user.delete");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribeToUser(Long userId, Long subscriptionUserId) {
        String query = resourcer.getString("user.subscription.save");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.setLong(2, subscriptionUserId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(User user) {
        String query = resourcer.getString("user.save");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUserBlockStatus().toString());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getUserRole().toString());
            statement.setString(7, user.getUserAuthorizationStatus().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAllSubscriptionsByUser(Long id) {
        String query = resourcer.getString("user.subscriptions.find.all.by.id");
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(DataMapper.toUser(resultSet));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void unsubscribeFromUser(Long userId, Long subscriptionUserId) {
        String query = resourcer.getString("user.subscription.delete");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.setLong(2, subscriptionUserId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void userSubscriptionDelete(Long userId) {
        String query = resourcer.getString("user.subscription.delete.by.user");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isSubscribedToUser(Long userId, Long subscriptionUserId) {
        String query = resourcer.getString("user.subscription.check");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.setLong(2, subscriptionUserId);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteUserNoteView(Long userId) {
        String query = resourcer.getString("user.note.views.delete.by.user");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
