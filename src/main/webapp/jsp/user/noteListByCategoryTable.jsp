<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <script
            src="https://code.jquery.com/jquery-3.6.3.min.js"
            integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
            crossorigin="anonymous">
    </script>
</head>
<table class="table">
    <thead>
    <tr>
        <th>Категории</th>
        <th>Содержимое</th>
        <th>Дата публикации</th>
        <th>Пользователь</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="note" items="${notes}">
        <c:if test="${note.getNoteStatus() == 'NOT_HIDDEN'}">
            <tr>
                <td>
                    <c:forEach var="category" items="${note.getCategories()}">
                        <span class="category-badge">${category.getCategory()}</span><br/>
                    </c:forEach>
                </td>
                <td>${note.getText()}</td>
                <td>${note.getDatePublication()}</td>
                <td>${note.getUser().getLogin()}</td>
                <td>
                    <div class="w-full flex items-center justify-center space-x-2">
                        <c:choose>
                            <c:when test="${viewedNotes[note]}">
                                <button class="text-gray-700 hover:text-gray-900" disabled>
                                    <span class="material-symbols-outlined">check_circle</span> Просмотрено
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button class="text-gray-700 hover:text-gray-900" onclick="viewedNote(${note.getNoteId()})">
                                    <span class="material-symbols-outlined">visibility</span> Просмотреть
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </td>
            </tr>
        </c:if>
    </c:forEach>
<%--    <c:forEach var="note" items="${notes}">--%>
<%--        <c:if test="${note.getNoteStatus() == 'NOT_HIDDEN'}">--%>
<%--            <c:forEach var="entry" items="${viewedNotes}">--%>
<%--                <c:set var="note" value="${entry.key}" />--%>
<%--                <c:set var="isViewed" value="${entry.value}" />--%>
<%--                    <tr>--%>
<%--                        <td>--%>
<%--                            <c:forEach var="category" items="${note.getCategories()}">--%>
<%--                                <span class="category-badge">${category.getCategory()}</span><br/>--%>
<%--                            </c:forEach>--%>
<%--                        </td>--%>
<%--                        <td>${note.getText()}</td>--%>
<%--                        <td>${note.getDatePublication()}</td>--%>
<%--                        <td>${note.getUser().getLogin()}</td>--%>
<%--                        <td>--%>
<%--                            <div class="w-full flex items-center justify-center space-x-2">--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${isViewed}">--%>
<%--                                        <button class="text-gray-700 hover:text-gray-900" onclick="notViewedNote(${note.getNoteId()})">--%>
<%--                                            <span class="material-symbols-outlined">check_circle</span> Просмотрено--%>
<%--                                        </button>--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise>--%>
<%--                                        <button class="text-gray-700 hover:text-gray-900" onclick="viewedNote(${note.getNoteId()})">--%>
<%--                                            <span class="material-symbols-outlined">visibility</span> Просмотреть--%>
<%--                                        </button>--%>
<%--                                    </c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                            </div>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--            </c:forEach>--%>
<%--        </c:if>--%>
<%--    </c:forEach>--%>
    </tbody>
</table>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
