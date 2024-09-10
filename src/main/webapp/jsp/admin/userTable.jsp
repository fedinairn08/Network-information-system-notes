<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.rsreu.notes.entity.enums.Roles" %>
<%@ page import="ru.rsreu.notes.entity.enums.UserBlockStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
    <script
            src="https://code.jquery.com/jquery-3.6.3.min.js"
            integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
            crossorigin="anonymous">
    </script>
</head>
<table class="table">
    <thead>
    <tr>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Статус</th>
        <th>Роль в системе</th>
        <th>Авторизован</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="session" items="${sessions}">
        <tr>
            <td>${session.getUser().getLastName()}</td>
            <td>${session.getUser().getFirstName()}</td>
            <td>${session.getUser().userBlockStatus.getRussianName()}</td>
            <td>${session.getUser().userRole.getRussianName()}</td>
            <td>${session.status.getRussianName()}</td>
            <td>
                <c:if test="${role.equals(Roles.ADMIN)}">
                    <div class="w-full flex items-center justify-center space-x-2">
                        <button class="text-gray-700 hover:text-gray-900" name="submit"
                                onclick="deleteUser(${session.getUser().getUserId()})">
                            <span class="material-symbols-outlined">delete</span>Удалить
                        </button>
                        <button class="text-gray-700 hover:text-gray-900" name="submit"
                                onclick="changeUser(${session.getUser().getUserId()})">
                            <span class="material-symbols-outlined">edit</span>Изменить
                        </button>
                    </div>
                </c:if>
                <c:if test="${role.equals(Roles.MODERATOR)}">
                    <c:if test="${session.getUser().getUserBlockStatus().equals(UserBlockStatus.NOT_BLOCKED)}">
                        <div class="w-full flex items-center justify-center space-x-2">
                            <button class="text-gray-700 hover:text-gray-900" onclick="blockUser(${session.getUser().getUserId()})">
                                <span class="material-symbols-outlined">lock</span>Заблокировать
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${session.getUser().getUserBlockStatus().equals(UserBlockStatus.BLOCKED)}">
                        <div class="w-full flex items-center justify-center space-x-2">
                            <button class="text-gray-700 hover:text-gray-900" onclick="unblockUser(${session.getUser().getUserId()})">
                                <span class="material-symbols-outlined">lock_open</span>Разблокировать
                            </button>
                        </div>
                    </c:if>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
