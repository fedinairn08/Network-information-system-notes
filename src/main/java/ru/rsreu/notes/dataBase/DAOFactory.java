package ru.rsreu.notes.dataBase;

import ru.rsreu.notes.dataBase.controller.CategoryDAOImpl;
import ru.rsreu.notes.dataBase.controller.NoteDAOImpl;
import ru.rsreu.notes.dataBase.controller.SessionDAOImpl;
import ru.rsreu.notes.dataBase.controller.UserDAOImpl;
import ru.rsreu.notes.dataBase.dao.CategoryDAO;
import ru.rsreu.notes.dataBase.dao.NoteDAO;
import ru.rsreu.notes.dataBase.dao.SessionDAO;
import ru.rsreu.notes.dataBase.dao.UserDAO;

public class DAOFactory {
    public static UserDAO getUserDAO() {return UserDAOImpl.getInstance();}
    public static SessionDAO getSessionDAO() {return SessionDAOImpl.getInstance();}
    public static NoteDAO getNoteDAO() {return NoteDAOImpl.getInstance();}
    public static CategoryDAO getCategoryDAO() {return CategoryDAOImpl.getInstance();
    }
}
