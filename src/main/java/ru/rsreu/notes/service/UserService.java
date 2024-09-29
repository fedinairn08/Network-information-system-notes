package ru.rsreu.notes.service;

import lombok.RequiredArgsConstructor;
import ru.rsreu.notes.dataBase.DAOFactory;
import ru.rsreu.notes.dataBase.dao.UserDAO;
import ru.rsreu.notes.entity.Category;
import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.entity.enums.UserAuthorizationStatus;
import ru.rsreu.notes.entity.enums.UserBlockStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
public class UserService {
    private static UserService instance;
    private final UserDAO userDAO;
    private final NoteService noteService;

    public static UserService getInstance() {
        synchronized (UserService.class) {
            if (instance == null) {
                instance = new UserService(DAOFactory.getUserDAO(), ServiceFactory.getNoteService());
            }
        }
        return instance;
    }

    public User getUser(String login) {
        return userDAO.findUserByLogin(login);
    }

    public User getUser(Long id) {
        return userDAO.findUserById(id);
    }

    public void save(User user) {
        userDAO.save(user);
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void blockUser(Long userId) {
        User user = this.getUser(userId);
        this.updateUser(user.setUserBlockStatus(UserBlockStatus.BLOCKED));
    }

    public void unblockUser(Long userId) {
        User user = this.getUser(userId);
        this.updateUser(user.setUserBlockStatus(UserBlockStatus.NOT_BLOCKED));
    }

    public void deleteUser(Long userId) {
        userDAO.delete(userId);
    }

    public void subscribeToUser(Long userId, Long subscriptionUserId) {
        userDAO.subscribeToUser(userId, subscriptionUserId);
    }

    public List<User> findAllSubscriptionsByUser(Long id) {return userDAO.findAllSubscriptionsByUser(id);}
    public void unsubscribeFromUser(Long userId, Long subscriptionUserId) {
        userDAO.unsubscribeFromUser(userId, subscriptionUserId);
    }

    public void userSubscriptionDelete(Long userId) {
        userDAO.userSubscriptionDelete(userId);
    }

    public void updateUserStatusNotAuthorized(Long userId) {
        User user = this.getUser(userId);
        this.updateUser(user.setUserAuthorizationStatus(UserAuthorizationStatus.NOT_AUTHORIZED));
    }

    public void updateUserStatusAuthorized(Long userId) {
        User user = this.getUser(userId);
        this.updateUser(user.setUserAuthorizationStatus(UserAuthorizationStatus.AUTHORIZED));
    }

    public boolean isSubscribedToUser(Long userId, Long subscriptionUserId) {
        return userDAO.isSubscribedToUser(userId, subscriptionUserId);
    }

    public void deleteUserNoteView(Long userId) {
        userDAO.deleteUserNoteView(userId);
    }
}
