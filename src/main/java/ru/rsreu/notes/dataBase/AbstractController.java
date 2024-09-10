package ru.rsreu.notes.dataBase;

import ru.rsreu.notes.resourcer.ProjectResourcer;
import ru.rsreu.notes.resourcer.Resourcer;

import java.sql.Connection;

public abstract class AbstractController {
    protected Connection connection;
    protected Resourcer resourcer;

    public AbstractController(){
        this.connection = ConnectionPool.getConnection();
        this.resourcer = ProjectResourcer.getInstance();
    }
}
