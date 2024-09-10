<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Авторизация</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<header>
    <h1>Заметки</h1>
</header>
<div class="auth-container">
    <div>
        <img class="displayed" src="${pageContext.request.contextPath}/images/notebook.jpg" alt="Заметки">
    </div>
    <form name="AuthorizationForm" method="POST" action="login" autocomplete="off">
        <div class="input">
            <input type="auth" name="login" placeholder="Введите логин" >
            <input type="auth" name="password" placeholder="Введите пароль" >
        </div>
        <div class="button-container">
            <button class="submit">Войти</button>
        </div>
    </form>
</div>
</body>
</html>
