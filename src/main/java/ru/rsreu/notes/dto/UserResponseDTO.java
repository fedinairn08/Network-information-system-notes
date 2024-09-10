package ru.rsreu.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.rsreu.notes.entity.User;
import ru.rsreu.notes.entity.enums.SessionStatus;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long session_id;
    private User user;
    private Timestamp activeUntil;
    private SessionStatus status;
}
