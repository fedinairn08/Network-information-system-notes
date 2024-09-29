function deleteUser(userId) {
    $.post("deleteUser", {user_id: userId}, function () {
        console.log("deleteUser");
        window.location.reload();
    }).fail(function () {
        console.log("deleteUser error!");
    });
}

function deleteNote(noteId) {
    $.post("deleteNote", {note_id: noteId}, function () {
        console.log("deleteNote");
        window.location.reload();
    }).fail(function () {
        console.log("deleteNote error!");
    });
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
        updateButtonToUnblock(userId);
    }).fail(function () {
        console.log("blockUser error!");
    });
}

function unblockUser(userId) {
    $.post("unblockUser", {user_id: userId}, function () {
        console.log("unblockUser");
        updateButtonToBlock(userId);
    }).fail(function () {
        console.log("unblockUser error!");
    });
}

function updateButtonToUnblock(userId) {
    const button = document.querySelector(`button[onclick="blockUser(${userId})"]`);
    if (button) {
        button.innerHTML = '<span class="material-symbols-outlined">lock_open</span> Разблокировать';
        button.setAttribute('onclick', `unblockUser(${userId})`);
    }
}

function updateButtonToBlock(userId) {
    const button = document.querySelector(`button[onclick="unblockUser(${userId})"]`);
    if (button) {
        button.innerHTML = '<span class="material-symbols-outlined">lock</span> Заблокировать';
        button.setAttribute('onclick', `blockUser(${userId})`);
    }
}


function hideNote(noteId) {
    $.post("hideNote", {note_id: noteId}, function () {
        console.log("hideNote");
        updateButtonToShow(noteId);
    }).fail(function () {
        console.log("hideNote error!");
    });
}

function unlockNote(noteId) {
    $.post("unlockNote", {note_id: noteId}, function () {
        console.log("unlockNote");
        updateButtonToHide(noteId);
    }).fail(function () {
        console.log("unlockNote error!");
    });
}

function updateButtonToShow(noteId) {
    const button = document.querySelector(`button[onclick="hideNote(${noteId})"]`);
    if (button) {
        button.innerHTML = '<span class="material-symbols-outlined">visibility</span>Показать';
        button.setAttribute('onclick', `unlockNote(${noteId})`);
    }
}

function updateButtonToHide(noteId) {
    const button = document.querySelector(`button[onclick="unlockNote(${noteId})"]`);
    if (button) {
        button.innerHTML = '<span class="material-symbols-outlined">visibility_off</span>Скрыть';
        button.setAttribute('onclick', `hideNote(${noteId})`);
    }
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
        window.location.reload();
    }).fail(function () {
        console.log("unsubscribeFromCategory error!");
    });
}

function subscribeToUser(subscriptionUserId) {
    $.post("subscribeToUser", {subscription_user_id: subscriptionUserId}, function () {
        console.log("subscribeToUser");
        window.location.reload();
    }).fail(function () {
        console.log("subscribeToUser error!");
    });
}

function unsubscribeFromUser(subscriptionUserId) {
    $.post("unsubscribeFromUser", {subscription_user_id: subscriptionUserId}, function () {
        console.log("unsubscribeFromUser");
        window.location.reload();
    }).fail(function () {
        console.log("unsubscribeFromUser error!");
    });
    window.location.reload();
}

function viewNote(noteId) {
    $.post("viewNote", {note_id: noteId}, function () {
        console.log("viewNote");
    }).fail(function () {
        console.log("viewNote error!");
    });
    window.location.reload();
}

function noteListByCategory(categoryId) {
    redirect('noteListByCategory',{category_id: categoryId});
}
function viewedNote(noteId) {
    $.post("viewedNote", {note_id: noteId}, function () {
        console.log("viewedNote");
        document.getElementById(`viewNoteBtn${noteId}`).disabled = true;
        document.getElementById(`viewNoteBtn${noteId}`).innerHTML = '<span class="material-symbols-outlined">check_circle</span> Просмотрено';
    }).fail(function () {
        console.log("viewedNote error!");
    });
    window.location.reload();
}

function notViewedNote(noteId) {
    $.post("notViewedNote", {note_id: noteId}, function () {
        console.log("notViewedNote");
        window.location.reload();
    }).fail(function () {
        console.log("notViewedNote error!");
    });
}