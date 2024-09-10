package ru.rsreu.notes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.rsreu.notes.entity.enums.Roles;
import ru.rsreu.notes.entity.enums.UserBlockStatus;

import javax.security.auth.Subject;
import java.security.Principal;

/**
 * Entity class representing a user of the system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User implements Principal {
    /**
     * Unique identifier for the user.
     */
    private Long userId;
    /**
     * Block status of the user (blocked or not blocked).
     */
    private UserBlockStatus userBlockStatus;
    /**
     * Login name of the user.
     */
    private String login;
    /**
     * Password of the user.
     */
    private String password;
    /**
     * First name of the user.
     */
    private String firstName;
    /**
     * Last name of the user.
     */
    private String lastName;
    /**
     * Role of the user in the system (admin, moderator, user).
     */
    private Roles userRole;

    /**
     * Returns the login name of the user.
     * @return The login name of the user.
     */
    @Override
    public String getName() {
        return login;
    }
}
