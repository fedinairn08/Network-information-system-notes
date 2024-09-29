package ru.rsreu.notes.utils.enums;

public enum Path {
    LOGIN("/login"),
    USERS("/users"),
    USER_INFO("/userInfo"),
    USER_PROFILE("/userProfile"),
    ADMIN_PROFILE("/adminProfile"),
    ADD_USER("/addUser"),
    CHANGE_USER("/changeUser"),
    USER_LIST("/userList"),
    ONLY_USER_LIST("/onlyUserList"),
    MODER_PROFILE("/moderProfile"),
    ALL_NOTE_LIST("/allNoteList"),
    NOTE_LIST("/noteList"),
    NOTE_LIST_BY_CATEGORY("/noteListByCategory"),
    LOGOUT("/logout"),
    DELETE_USER("/deleteUser"),
    DELETE_NOTE("/deleteNote"),
    BLOCK_USER("/blockUser"),
    UNBLOCK_USER("/unblockUser"),
    HIDE_NOTE("/hideNote"),
    UNLOCK_NOTE("/unlockNote"),
    ADD_CATEGORY("/addCategory"),
    CATEGORY_LIST("/categoryList"),
    CATEGORY_SUBSCRIPTIONS_LIST("/categorySubscriptionsList"),
    USER_SUBSCRIPTIONS_LIST("/userSubscriptionsList"),
    ADD_NOTE("/addNote"),
    SUBSCRIBE_TO_CATEGORY("/subscribeToCategory"),
    UNSUBSCRIBE_FROM_CATEGORY("/unsubscribeFromCategory"),
    SUBSCRIBE_TO_USER("/subscribeToUser"),
    UNSUBSCRIBE_FROM_USER("/unsubscribeFromUser"),
    NOT_FOUND("404"),
    VIEWED_NOTE("/viewedNote"),
    NOT_VIEWED_NOTE("/notViewedNote");

    private final String route;

    Path(String route) {
        this.route = route;
    }

    public String getRelativePath() {
        return this.route;
    }

    public String getAbsolutePath() {
        return this.route.substring(1);
    }
}
