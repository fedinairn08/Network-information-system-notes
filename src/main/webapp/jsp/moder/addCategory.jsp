<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавление категории</title>
    <link rel="stylesheet" href="https://unpkg.com/flowbite@1.5.5/dist/flowbite.min.css"/>
    <style>
        <%@include file="../../css/styles.css" %>

    </style>
</head>
<body class="other-page">
<div class="addCategory">
    <div class="container">
        <div class="addCategory-inner">
            <form name="AddCategoryForm" method="POST" action="addCategory">
                <div class="addCategory-title title">
                    <div class="header-user dark-text">Добавление категории</div>
                </div>
                <br>
                <div class="addCategory-category">
                    <label for="category" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white custom-label">Категория:</label>
                    <input type="text" id="category" name="category" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="Спорт" required />
                </div>
                <br>
                <div class="button-container">
                    <button class="submit" type="submit" name="submit">Добавить категорию</button>
                </div>
                <button type="button" class="back-button text-white bg-gradient-to-r from-purple-500 to-blue-500 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-cyan-300
                dark:focus:ring-cyan-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center mr-2" onclick="window.history.back()">Назад</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>