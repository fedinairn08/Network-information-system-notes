<%@ page import="ru.rsreu.notes.entity.enums.Roles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Изменение пользователя</title>
    <link rel="stylesheet" href="https://unpkg.com/flowbite@1.5.5/dist/flowbite.min.css"/>
    <style>
        <%@include file="../../css/styles.css" %>
    </style>
</head>
<body class="other-page">
<div>
    <button type="button" class="back-button text-white bg-gradient-to-r from-purple-500 to-blue-500 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-cyan-300
                dark:focus:ring-cyan-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center mr-2" onclick="window.history.back()">Назад</button>

    <div class="container">
        <div>
            <form name="ChangeUserForm" method="POST" action="changeUser">
                <div class="changeUser-title title">
                    <div class="header-user dark-text">Изменение пользователя</div>
                </div>
                <br>
                <div class="hidden">
                    <input type="text" id="id_id" name="id" class="changeUser-firstname-input" value="${user.userId}" />
                </div>
                <div class="changeUser-firstname">
                    <label for="firstname" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white custom-label">Имя:</label>
                    <input type="text" id="firstname" name="first_name" value="${user.firstName}" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" />
                </div>
                <div class="changeUser-lastname">
                    <label for="lastname" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white custom-label">Фамилия:</label>
                    <input type="text" id="lastname" name="last_name" value="${user.lastName}" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" />
                </div>
                <div class="changeUser-login">
                    <label for="login" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white custom-label">Логин:</label>
                    <input type="text" id="login" name="login" value="${user.login}" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" />
                </div>
                <div class="changeUser-password">
                    <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white custom-label">Пароль:</label>
                    <input type="text" id="password" name="password" value="${user.password}" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" />
                </div>
                <div class="changeUser-role-container">
                    <div class="changeUser-role">
                        <label>Выберите роль:</label>
                        <select id="selectMark" name="role">
                            <c:forEach items="${Roles.getAllRoles()}" var="role">
                                <option value="${role}" ${user.userRole.equals(role) ? 'selected' : ''}>${role.getRussianName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <br>
                <div class="button-container">
                    <button class="submit" type="submit" name="submit">Сохранить изменения</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
