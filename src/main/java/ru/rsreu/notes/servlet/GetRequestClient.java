package ru.rsreu.notes.servlet;

import ru.rsreu.notes.servlet.admin.getRequest.AddUserCommandGet;
import ru.rsreu.notes.servlet.admin.getRequest.AdminProfileCommandGet;
import ru.rsreu.notes.servlet.admin.getRequest.ChangeUserCommandGet;
import ru.rsreu.notes.servlet.admin.getRequest.UserListCommandGet;
import ru.rsreu.notes.servlet.getRequest.EmptyCommandGet;
import ru.rsreu.notes.servlet.getRequest.LoginCommandGet;
import ru.rsreu.notes.servlet.getRequest.LogoutCommandGet;
import ru.rsreu.notes.servlet.moder.getRequest.AddCategoryCommandGet;
import ru.rsreu.notes.servlet.moder.getRequest.AllNoteListCommandGet;
import ru.rsreu.notes.servlet.moder.getRequest.ModerProfileCommandGet;
import ru.rsreu.notes.servlet.user.getRequest.*;
import ru.rsreu.notes.utils.enums.Path;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetRequestClient extends HttpRequestClient {

    @Override
    public Command initCommand(String path, ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        if (isPath(path, Path.ADD_USER)) {
            return new AddUserCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.LOGIN)) {
            return new LoginCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.NOT_FOUND)) {
            return new EmptyCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.LOGOUT)) {
            return new LogoutCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.ADMIN_PROFILE)) {
            return new AdminProfileCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.CHANGE_USER)) {
            return new ChangeUserCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.USER_LIST)) {
            return new UserListCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.ADD_CATEGORY)) {
            return new AddCategoryCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.ALL_NOTE_LIST)) {
            return new AllNoteListCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.MODER_PROFILE)) {
            return new ModerProfileCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.ADD_NOTE)) {
            return new AddNoteCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.CATEGORY_LIST)) {
            return new CategoryListCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.CATEGORY_SUBSCRIPTIONS_LIST)) {
            return new CategorySubscriptionsListCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.NOTE_LIST_BY_CATEGORY)) {
            return new NoteListByCategoryCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.NOTE_LIST)) {
            return new NoteListCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.ONLY_USER_LIST)) {
            return new OnlyUserListCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.USER_PROFILE)) {
            return new UserProfileCommandGet(servletContext, request, response);
        } else if (isPath(path, Path.USER_SUBSCRIPTIONS_LIST)) {
            return new UserSubscriptionsListCommandGet(servletContext, request, response);
        }
        return null;
    }
}
