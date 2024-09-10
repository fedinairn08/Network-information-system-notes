<%@ page import="ru.rsreu.notes.entity.enums.Roles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавление пользователя</title>
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
        <div class="addUser-inner">
            <form name="AddUserForm" method="POST" action="addUser">
                <div class="addUser-title title">
                    <div class="header-user dark-text">Добавление пользователя</div>
                </div>
                <br>
                <div class="hidden">
                    <input type="text" id="id_id" name="id" class="addUser-firstname-input" value="${user.userId}">
                </div>
                <div class="addUser-firstname">
                    <label for="firstname" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white custom-label">Имя:</label>
                    <input type="text" id="firstname" name="first_name" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="Ирина" required />
                </div>
                <div class="addUser-lastname">
                    <label for="lastname" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white custom-label">Фамилия:</label>
                    <input type="text" id="lastname" name="last_name" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="Федина" required />
                </div>
                <div class="addUser-login">
                    <label for="login" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white custom-label">Логин:</label>
                    <input type="text" id="login" name="login" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="irina" required />
                </div>
                <div class="addUser-password">
                    <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white custom-label">Пароль:</label>
                    <input type="text" id="password" name="password" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="•" required />
                </div>
                <div class="addUser-role-container">
                    <div class="addUser-role">
                        <label>Выберите роль:</label>
                        <select id="selectMark" name="role">
                            <c:forEach items="${Roles.getAllRoles()}" var="role">
                                <c:if test="${user != null && user.userRole.equals(role)}">
                                    <option value="${role}" selected>${role.getRussianName()}</option>
                                </c:if>
                                <c:if test="${user == null || !user.userRole.equals(role)}">
                                    <option value="${role}">${role.getRussianName()}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <br>
                <div class="button-container">
                    <button class="submit" type="submit" name="submit">Добавить пользователя</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>