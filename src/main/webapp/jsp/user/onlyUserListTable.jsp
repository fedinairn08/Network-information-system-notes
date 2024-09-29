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
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="session" items="${sessions}">
        <c:if test="${session.getUser().getUserRole() == Roles.USER && session.getUser().getUserBlockStatus() == UserBlockStatus.NOT_BLOCKED}">
            <tr>
                <td>${session.getUser().getLastName()}</td>
                <td>${session.getUser().getFirstName()}</td>
                <td>
                    <div class="w-full flex items-center justify-center space-x-2">
                        <c:choose>
                            <c:when test="${userSubscriptionStatus[session.getUser().getUserId()] == true}">
                                <button class="text-gray-700 hover:text-gray-900" onclick="unsubscribeFromUser(${session.getUser().getUserId()})">
                                    <span class="material-symbols-outlined">cancel</span> Отписаться
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button class="text-gray-700 hover:text-gray-900" onclick="subscribeToUser(${session.getUser().getUserId()})">
                                    <span class="material-symbols-outlined">subscriptions</span> Подписаться
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>