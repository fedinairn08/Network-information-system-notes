package ru.rsreu.notes.dataBase;

import ru.rsreu.notes.entity.Category;
import ru.rsreu.notes.entity.Note;
import ru.rsreu.notes.entity.Session;
import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.entity.enums.NoteStatus;
import ru.rsreu.notes.entity.enums.Roles;
import ru.rsreu.notes.entity.enums.UserAuthorizationStatus;
import ru.rsreu.notes.entity.enums.UserBlockStatus;
import ru.rsreu.notes.dataBase.controller.NoteDAOImpl;

import java.sql.*;

public class DataMapper {
    public static User toUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("user_id"),
                UserBlockStatus.valueOf(resultSet.getString("user_block_status")),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                Roles.valueOf(resultSet.getString("user_role")),
                UserAuthorizationStatus.valueOf(resultSet.getString("user_authorization_status"))
        );
    }

    public static Session toSession(ResultSet resultSet) throws SQLException {
        return new Session(
                resultSet.getLong("session_id"),
                DataMapper.toUser(resultSet),
                resultSet.getTimestamp("active_until")
        );
    }

    public static Note toNote(ResultSet resultSet) throws SQLException {
        NoteDAOImpl noteDAO = NoteDAOImpl.getInstance();

        return new Note(
                resultSet.getLong("note_id"),
                noteDAO.findCategoriesByNoteId(resultSet.getLong("note_id")),
                NoteStatus.valueOf(resultSet.getString("note_status")),
                resultSet.getString("text"),
                DataMapper.toUser(resultSet),
                new Date(resultSet.getTimestamp("date_publication").getTime())
        );
    }

    public static Category toCategory(ResultSet resultSet) throws SQLException {
        return new Category(
                resultSet.getLong("category_id"),
                resultSet.getString("category")
        );
    }
}
