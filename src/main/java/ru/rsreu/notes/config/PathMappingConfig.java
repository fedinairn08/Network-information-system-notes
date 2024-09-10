package ru.rsreu.notes.config;

import ru.rsreu.notes.servlet.EmptyCommand;
import ru.rsreu.notes.servlet.FrontCommand;
import ru.rsreu.notes.servlet.LoginCommand;
import ru.rsreu.notes.servlet.LogoutCommand;
import ru.rsreu.notes.servlet.admin.*;
import ru.rsreu.notes.servlet.moder.*;
import ru.rsreu.notes.servlet.user.*;
import ru.rsreu.notes.utils.enums.Path;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PathMappingConfig {
    private static final Map<Path, FrontCommand> commands = Map.ofEntries(
            Map.entry(Path.LOGIN, new LoginCommand()),
            Map.entry(Path.LOGOUT, new LogoutCommand()),
            Map.entry(Path.NOT_FOUND, new EmptyCommand()),

            Map.entry(Path.ADMIN_PROFILE, new AdminProfileCommand()),
            Map.entry(Path.ADD_USER, new AddUserCommand()),
            Map.entry(Path.USER_LIST, new UserListCommand()),
            Map.entry(Path.CHANGE_USER, new ChangeUserCommand()),
            Map.entry(Path.DELETE_USER, new DeleteUserCommand()),

            Map.entry(Path.MODER_PROFILE, new ModerProfileCommand()),
            Map.entry(Path.ALL_NOTE_LIST, new AllNoteListCommand()),
            Map.entry(Path.NOTE_LIST, new NoteListCommand()),
            Map.entry(Path.CATEGORY_LIST, new CategoryListCommand()),
            Map.entry(Path.ADD_CATEGORY, new AddCategoryCommand()),
            Map.entry(Path.BLOCK_USER, new BlockUserCommand()),
            Map.entry(Path.UNBLOCK_USER, new UnblockUserCommand()),
            Map.entry(Path.HIDE_NOTE, new HideNoteCommand()),
            Map.entry(Path.UNLOCK_NOTE, new UnlockNoteCommand()),
            Map.entry(Path.DELETE_NOTE, new DeleteNoteCommand()),

            Map.entry(Path.USER_PROFILE, new UserProfileCommand()),
            Map.entry(Path.ADD_NOTE, new AddNoteCommand()),
            Map.entry(Path.ONLY_USER_LIST, new OnlyUserListCommand()),
            Map.entry(Path.SUBSCRIBE_TO_CATEGORY, new SubscribeCategoryCommand()),
            Map.entry(Path.UNSUBSCRIBE_FROM_CATEGORY, new UnsubscribeCategoryCommand()),
            Map.entry(Path.SUBSCRIBE_TO_USER, new SubscribeUserCommand()),
            Map.entry(Path.CATEGORY_SUBSCRIPTIONS_LIST, new CategorySubscriptionsListCommand()),
            Map.entry(Path.USER_SUBSCRIPTIONS_LIST, new UserSubscriptionsListCommand()),
            Map.entry(Path.UNSUBSCRIBE_FROM_USER, new UnsubscribeUserCommand())
    );

    private static final List<Path> commandPaths = Arrays.asList(
            Path.LOGIN,
            Path.LOGOUT,
            Path.CHANGE_USER,
            Path.DELETE_USER,
            Path.BLOCK_USER,
            Path.UNBLOCK_USER,
            Path.HIDE_NOTE,
            Path.UNLOCK_NOTE,
            Path.NOT_FOUND,
            Path.DELETE_NOTE,
            Path.ADMIN_PROFILE,
            Path.ADD_USER,
            Path.USER_LIST,
            Path.MODER_PROFILE,
            Path.ALL_NOTE_LIST,
            Path.ONLY_USER_LIST,
            Path.NOTE_LIST,
            Path.USER_PROFILE,
            Path.ADD_CATEGORY,
            Path.ADD_NOTE,
            Path.CATEGORY_LIST,
            Path.CATEGORY_SUBSCRIPTIONS_LIST,
            Path.USER_SUBSCRIPTIONS_LIST,
            Path.SUBSCRIBE_TO_CATEGORY,
            Path.UNSUBSCRIBE_FROM_CATEGORY,
            Path.SUBSCRIBE_TO_USER,
            Path.UNSUBSCRIBE_FROM_USER
    );

    public static FrontCommand getCommand(String allPath) {
        for (Path path : commandPaths) {
            if (path.getRelativePath().equalsIgnoreCase(allPath.split("&")[0])) {
                return commands.get(path);
            }
        }
        return new EmptyCommand();
    }
}
