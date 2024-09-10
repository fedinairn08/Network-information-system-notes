package ru.rsreu.notes.service;

import lombok.RequiredArgsConstructor;
import ru.rsreu.notes.dataBase.DAOFactory;
import ru.rsreu.notes.dataBase.dao.SessionDAO;
import ru.rsreu.notes.dto.UserResponseDTO;
import ru.rsreu.notes.entity.Session;
import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.entity.enums.Roles;
import ru.rsreu.notes.entity.enums.SessionStatus;
import ru.rsreu.notes.entity.enums.UserBlockStatus;
import ru.rsreu.notes.utils.SessionValidator;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SessionService {
    public static final long SESSION_TIME= 1000 * 60 * 60;
    private static SessionService instance;
    private final SessionDAO sessionDAO;
    private final UserService userService;
    public static SessionService getInstance() {
        synchronized (SessionService.class) {
            if (instance == null) {
                instance = new SessionService(DAOFactory.getSessionDAO(), ServiceFactory.getUserService());
            }
        }
        return instance;
    }

    public Optional<Session> getSession(Long userId) {
        return sessionDAO.findByUserId(userId);
    }

    public User createSession(String login, String password) throws Exception {
        User user = userService.getUser(login);

        if (user.getUserBlockStatus().equals(UserBlockStatus.BLOCKED) || !user.getPassword().equals(password)) {
            throw new Exception("Not authorized");
        }

        Timestamp activeUntil = new Timestamp(System.currentTimeMillis() + SESSION_TIME);
        Optional<Session> optionalSession = sessionDAO.findByUserId(user.getUserId());
        Session session = optionalSession.isPresent() ?
                optionalSession.get().setActiveUntil(activeUntil) :
                new Session(user, activeUntil);

        if (!optionalSession.isPresent()) {
            sessionDAO.save(session);
        } else {
            sessionDAO.update(session);
        }

        return user;
    }

    public List<Session> getAllSessions() {
        return sessionDAO.findAll();
    }

    public List<UserResponseDTO> getAllUserList(User user) {
        List<Session> sessions = this.getAllSessions();
        return sessions.stream().filter(session ->
                        user.getUserRole().equals(Roles.ADMIN) || !session.getUser().getUserRole().equals(Roles.ADMIN))
                .map(session -> new UserResponseDTO(
                        session.getSessionId(),
                        session.getUser(),
                        session.getActiveUntil(),
                        session.getActiveUntil() != null && SessionValidator.checkValid(session) ?
                                SessionStatus.AUTHORIZED : SessionStatus.NOT_AUTHORIZED
                )).filter(userResponseDTO -> !userResponseDTO.getUser().getUserId().equals(user.getUserId()))
                .collect(Collectors.toList());
    }

    public void deleteSession(Long userId) {
        sessionDAO.delete(userId);
    }
}
