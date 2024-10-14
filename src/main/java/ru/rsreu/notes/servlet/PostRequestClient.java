package ru.rsreu.notes.servlet;

import ru.rsreu.notes.servlet.admin.postRequest.AddUserCommandPost;
import ru.rsreu.notes.servlet.admin.postRequest.ChangeUserCommandPost;
import ru.rsreu.notes.servlet.admin.postRequest.DeleteUserCommandPost;
import ru.rsreu.notes.servlet.moder.postRequest.*;
import ru.rsreu.notes.servlet.postRequest.LoginCommandPost;
import ru.rsreu.notes.servlet.user.postRequest.*;
import ru.rsreu.notes.utils.enums.Path;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostRequestClient extends HttpRequestClient {
    @Override
    public ICommand initCommand(String path, ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
        if (isPath(path, Path.ADD_USER)) {
            return new AddUserCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.LOGIN)) {
            return new LoginCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.CHANGE_USER)) {
            return new ChangeUserCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.DELETE_USER)) {
            return new DeleteUserCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.ADD_CATEGORY)) {
            return new AddCategoryCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.BLOCK_USER)) {
            return new BlockUserCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.HIDE_NOTE)) {
            return new HideNoteCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.UNBLOCK_USER)) {
            return new UnblockUserCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.UNLOCK_NOTE)) {
            return new UnlockNoteCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.ADD_NOTE)) {
            return new AddNoteCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.DELETE_NOTE)) {
            return new DeleteNoteCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.NOT_VIEWED_NOTE)) {
            return new NotViewedNoteCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.SUBSCRIBE_TO_CATEGORY)) {
            return new SubscribeCategoryCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.SUBSCRIBE_TO_USER)) {
            return new SubscribeUserCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.UNSUBSCRIBE_FROM_CATEGORY)) {
            return new UnsubscribeCategoryCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.UNSUBSCRIBE_FROM_USER)) {
            return new UnsubscribeUserCommandPost(servletContext, request, response);
        } else if (isPath(path, Path.VIEWED_NOTE)) {
            return new ViewedNoteCommandPost(servletContext, request, response);
        }
        return null;
    }
}
