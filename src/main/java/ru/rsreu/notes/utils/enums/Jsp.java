package ru.rsreu.notes.utils.enums;

public enum Jsp {
    LOGIN("/jsp/authorization.jsp"),
    USER_PROFILE("/jsp/user/userProfile.jsp"),
    ALL_NOTE_LIST("/jsp/moder/allNoteList.jsp"),
    NOTE_LIST("/jsp/user/noteList.jsp"),
    NOTE_LIST_BY_CATEGORY("/jsp/user/noteListByCategory.jsp"),
    CATEGORY_LIST("/jsp/user/categoryList.jsp"),
    CATEGORY_SUBSCRIPTIONS_LIST("/jsp/user/categorySubscriptionsList.jsp"),
    USER_SUBSCRIPTIONS_LIST("/jsp/user/userSubscriptionsList.jsp"),
    ADMIN_PROFILE("/jsp/admin/adminProfile.jsp"),
    ADD_USER("/jsp/admin/addUser.jsp"),
    CHANGE_USER("/jsp/admin/changeUser.jsp"),
    ADD_NOTE("/jsp/user/addNote.jsp"),
    USER_LIST("/jsp/admin/userList.jsp"),
    ONLY_USER_LIST("/jsp/user/onlyUserList.jsp"),
    MODER_PROFILE("/jsp/moder/moderProfile.jsp"),
    ADD_CATEGORY("/jsp/moder/addCategory.jsp");

    private final String route;

    Jsp(String route) {
        this.route = route;
    }

    public String getPath() {
        return this.route;
    }
}
