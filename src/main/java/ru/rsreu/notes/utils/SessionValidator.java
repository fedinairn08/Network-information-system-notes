package ru.rsreu.notes.utils;

import ru.rsreu.notes.entity.Session;

import java.sql.Timestamp;

public class SessionValidator {
    private SessionValidator(){

    }

    public static boolean checkValid(Session session) {
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        Timestamp sessionExpiredDate = session.getActiveUntil();

        return sessionExpiredDate.after(currentDate);
    }
}
