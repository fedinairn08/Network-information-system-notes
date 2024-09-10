package ru.rsreu.notes.entity.enums;

public enum NoteStatus {
    HIDDEN("Скрыта"),
    NOT_HIDDEN("Не скрыта");

    private final String noteStatus;

    NoteStatus(String noteStatus){
        this.noteStatus = noteStatus;
    }

    public String getRussianName() {
        return noteStatus;
    }
}
