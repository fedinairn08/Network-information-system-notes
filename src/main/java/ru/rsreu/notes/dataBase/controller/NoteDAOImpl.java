package ru.rsreu.notes.dataBase.controller;

import ru.rsreu.notes.dataBase.AbstractController;
import ru.rsreu.notes.dataBase.DataMapper;
import ru.rsreu.notes.dataBase.dao.NoteDAO;
import ru.rsreu.notes.entity.Note;
import ru.rsreu.notes.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAOImpl extends AbstractController implements NoteDAO {
    private static NoteDAOImpl instance;

    public static NoteDAOImpl getInstance() {
        synchronized (NoteDAOImpl.class) {
            if (instance == null) {
                instance = new NoteDAOImpl();
            }
        }
        return instance;
    }

    @Override
    public List<Note> findAll() {
        String query = resourcer.getString("note.find.all");
        List<Note> notes = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                notes.add(DataMapper.toNote(resultSet));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }

    @Override
    public List<Note> findAllByToUser(Long toUserId) {
        String query = resourcer.getString("note.find.all.user");
        List<Note> notes = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, toUserId);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                notes.add(DataMapper.toNote(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }

    @Override
    public List<Note> findAllByCategory(Long categoryId) {
        String query = resourcer.getString("note.find.all.category");
        List<Note> notes = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, categoryId);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                notes.add(DataMapper.toNote(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }

    @Override
    public void update(Note note) {
        String query = resourcer.getString("note.update");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, note.getNoteStatus().toString());
            statement.setString(2, note.getText());
            statement.setLong(3, note.getNoteId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long noteId) {
        String query = resourcer.getString("note.delete");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, noteId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNoteCategory(Long noteId) {
        String query = resourcer.getString("note.categories.delete");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, noteId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Note save(Note note) {
        String query = resourcer.getString("note.save");
        long generatedNoteId = 0;
        String[] returnCols = { "NOTE_ID" };

        try (PreparedStatement statement = connection.prepareStatement(query, returnCols)) {
            statement.setString(1, note.getNoteStatus().toString());
            statement.setString(2, note.getText());
            statement.setLong(3, note.getUser().getUserId());
            statement.setDate(4, note.getDatePublication());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                generatedNoteId = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Category category : note.getCategories()) {
            saveNoteCategories(note, generatedNoteId, category.getCategoryId());
        }

        note.setNoteId(generatedNoteId);
        return note;
    }

    @Override
    public void saveNoteCategories(Note note, Long noteId, Long categoryId) {
        String query = resourcer.getString("note.categories.save");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, noteId);
            statement.setLong(2, categoryId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUserNoteView(long userId, long noteId) {
        String query = resourcer.getString("user.note.views.save");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.setLong(2, noteId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isNoteViewedByUser(Long userId, Long noteId) {
        String query = resourcer.getString("note.view.check");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.setLong(2, noteId);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Note findNoteById(Long id) {
        String query = resourcer.getString("note.find.id");
        Note note = null;

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                note = DataMapper.toNote(resultSet);

                note.setCategories(findCategoriesByNoteId(note.getNoteId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return note;
    }

    @Override
    public void deleteUserNoteView(Long noteId) {
        String query = resourcer.getString("user.note.views.delete.by.note");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, noteId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNotesByUser(Long userId) {
        String query = resourcer.getString("note.delete.by.user");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserNoteViewsByUser(Long userId) {
        String query = resourcer.getString("user.note.views.delete");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUserNoteViews(Long userId, Long noteId) {
        String query = resourcer.getString("user.note.views.delete");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.setLong(2, noteId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNoteCategoriesByUser(Long userId) {
        String query = resourcer.getString("note.categories.delete.by.user");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> findCategoriesByNoteId(Long noteId) {
        String query = resourcer.getString("note.find.categories.by.id");
        List<Category> categories = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, noteId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                categories.add(DataMapper.toCategory(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }
}
