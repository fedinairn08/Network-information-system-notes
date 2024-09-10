<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Меню пользователя</title>
    <style>
        <%@include file="../../css/styles.css" %>
    </style>
    <link rel="stylesheet" href="https://unpkg.com/flowbite@1.5.5/dist/flowbite.min.css"/>
</head>
<body>
<div class="profile-page">
    <nav class="bg-white border-gray-200 px-2 py-2.5 rounded">
        <div class="container flex flex-wrap flex-col items-center justify-between mx-auto">
            <a class="flex items-center mb-2">
                <img src="${pageContext.request.contextPath}/images/notebook.jpg" class="h-6 mr-3"/>
                <span class="self-center text-xl font-semibold whitespace-nowrap">Сетевая информационная система "Заметки"</span>
            </a>
            <ul class="flex item-center space-x-1 p-1 mt-4 border border-gray-100 rounded-lg bg-gray-50">
                <li>
                    <a href="${pageContext.request.contextPath}/notes/addNote"
                       class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700">Добавление
                        заметки</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/notes/noteList"
                       class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700">Просмотр
                        заметок </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/notes/onlyUserList"
                       class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700">Просмотр
                        списка пользователей </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/notes/categoryList"
                       class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700">Просмотр
                        списка категорий </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/notes/categorySubscriptionsList"
                       class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700">Просмотр
                        списка подписок на категории </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/notes/userSubscriptionsList"
                       class="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700">Просмотр
                        списка подписок на пользователей </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/notes/logout"
                       class="block py-2 pl-3 pr-4 text-white bg-blue-700 rounded md:bg-transparent md:text-blue-700"
                       aria-current="page">Выйти из системы</a>
                </li>
            </ul>
        </div>

    </nav>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://unpkg.com/flowbite@1.5.5/dist/flowbite.js"></script>
</body>
</html>
