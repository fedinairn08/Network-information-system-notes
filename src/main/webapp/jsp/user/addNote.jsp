<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавление заметки</title>
    <link rel="stylesheet" href="https://unpkg.com/flowbite@1.5.5/dist/flowbite.min.css"/>
    <style>
        <%@include file="../../css/styles.css" %>
    </style>
</head>
<body class="other-page">
<div class="addNote">
    <button type="button" class="back-button text-white bg-gradient-to-r from-purple-500 to-blue-500 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-cyan-300
                dark:focus:ring-cyan-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center mr-2" onclick="window.history.back()">Назад</button>

    <div class="container-add-note">
        <div class="addNote-inner">
            <form name="AddNoteForm" method="POST" action="addNote">
                <div class="addNote-title title">
                    <div class="header-user dark-text">Добавление заметки</div>
                </div>
                <br>
                <div class="addNote-note-category">
                    <label for="category">Выберите категории:</label>
                    <select id="category" name="note_category[]" class="addNote-category-select" multiple>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.categoryId}">${category.category}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="addNote-text">
                    <div class="addNote-text-title">Текст</div>
                    <textarea id="text" name="text" class="addNote-text-input"></textarea>
                </div>
                <br>
                <div class="button-container">
                    <button class="submit" type="submit" name="submit">Добавить заметку</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
