package ru.rsreu.notes.service;

public class ServiceFactory {
    public static SessionService getSessionService() {return SessionService.getInstance();}
    public static UserService getUserService() {return UserService.getInstance();}
    public static NoteService getNoteService() {return NoteService.getInstance();}
    public static CategoryService getCategoryService() {return CategoryService.getInstance();}
}
