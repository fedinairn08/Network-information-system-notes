package ru.rsreu.notes.dataBase.controller;

import ru.rsreu.notes.dataBase.AbstractController;
import ru.rsreu.notes.dataBase.DataMapper;
import ru.rsreu.notes.dataBase.dao.SessionDAO;
import ru.rsreu.notes.entity.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionDAOImpl extends AbstractController implements SessionDAO {
    private static SessionDAOImpl instance;

    public static SessionDAOImpl getInstance() {
        synchronized (SessionDAOImpl.class) {
            if (instance == null) {
                instance = new SessionDAOImpl();
            }
        }
        return instance;
    }

    @Override
    public void save(Session session) {
        String query = resourcer.getString("session.save");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, session.getUser().getUserId());
            statement.setTimestamp(2, new Timestamp(session.getActiveUntil().getTime()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Session> findAll() {
        String query = resourcer.getString("session.find.all");
        List<Session> sessions = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                sessions.add(DataMapper.toSession(resultSet));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return sessions;
    }

    @Override
    public void delete(Long userId) {
        String query = resourcer.getString("session.delete");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Session> findByUserId(Long userId) {
        String query = resourcer.getString("session.find.by.id");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return Optional.of(DataMapper.toSession(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void update(Session session) {
        String query = resourcer.getString("session.update");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(2, session.getSessionId());
            statement.setDate(1, new Date(session.getActiveUntil().getTime()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
