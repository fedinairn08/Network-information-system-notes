function deleteUser(userId) {
    $.post("deleteUser", {user_id: userId}, function () {
        console.log("deleteUser");
    }).fail(function () {
        console.log("deleteUser error!");
    });
    window.location.reload();
}

function deleteNote(noteId) {
    $.post("deleteNote", {note_id: noteId}, function () {
        console.log("deleteNote");
    }).fail(function () {
        console.log("deleteNote error!");
    });
    window.location.reload();
}

function redirect(path, query) {
    const url = [
        window.location.origin,
        'notes',
        path
    ].join('/');

    const queryParams = query ? `?${new URLSearchParams(query).toString()}` : ''

    window.location.href = url + queryParams;
}

function changeUser(userId) {
    redirect('changeUser',{user_id: userId});
}

function blockUser(userId) {
    $.post("blockUser", {user_id: userId}, function () {
        console.log("blockUser");
    }).fail(function () {
        console.log("blockUser error!");
    });
    window.location.reload();
}

function unblockUser(userId) {
    $.post("unblockUser", {user_id: userId}, function () {
        console.log("unblockUser");
    }).fail(function () {
        console.log("unblockUser error!");
    });
    window.location.reload();
}

function hideNote(noteId) {
    $.post("hideNote", {note_id: noteId}, function () {
        console.log("hideNote");
    }).fail(function () {
        console.log("hideNote error!");
    });
    window.location.reload();
}

function unlockNote(noteId) {
    $.post("unlockNote", {note_id: noteId}, function () {
        console.log("unlockNote");
    }).fail(function () {
        console.log("unlockNote error!");
    });
    window.location.reload();
}

function subscribeToCategory(categoryId) {
    $.post("subscribeToCategory", {category_id: categoryId}, function () {
        console.log("subscribeToCategory");
    }).fail(function () {
        console.log("subscribeToCategory error!");
    });
    window.location.reload();
}

function unsubscribeFromCategory(categoryId) {
    $.post("unsubscribeFromCategory", {category_id: categoryId}, function () {
        console.log("unsubscribeFromCategory");
    }).fail(function () {
        console.log("unsubscribeFromCategory error!");
    });
    window.location.reload();
}

function subscribeToUser(subscriptionUserId) {
    $.post("subscribeToUser", {subscription_user_id: subscriptionUserId}, function () {
        console.log("subscribeToUser");
    }).fail(function () {
        console.log("subscribeToUser error!");
    });
    window.location.reload();
}

function unsubscribeFromUser(subscriptionUserId) {
    $.post("unsubscribeFromUser", {subscription_user_id: subscriptionUserId}, function () {
        console.log("unsubscribeFromUser");
    }).fail(function () {
        console.log("unsubscribeFromUser error!");
    });
    window.location.reload();
}