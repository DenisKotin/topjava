<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 07.06.2020
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="meal" class="ru.javawebinar.topjava.model.MealTo" scope="request" />



<html>
<head>
    <title>Meal List</title>
    <style>
        .normal{color: green}
        .exceeded{color: red}
        ьььь
    </style>
</head>
<body>
    <h2><a href="index.html">Home</a></h2>
    <hr>

    <table border="3" cellpadding="8" >
        <h3> Meal List</h3>
        <tr>
            <tr style="background-color: azure">
            <th> Дата время </th>
            <th> Описание  </th>
            <th> Калории  </th>
            <th> Редактирование  </th>
            <th> Удаление  </th>
        </tr>


        <c:forEach items="${requestScope.maleList}" var="meal" >

            <tr>
                <tr class="${meal.excess ? 'exceeded': 'normal'}">

                <td><c:out value="${meal.getFormatedDateTime()}"/></td>
                <td><c:out value="${meal.description}" /></td>
                <td><c:out value="${meal.calories}"/></td>
                <td><a href="meal?action=edit&userId=${meal.getId()}"> edit</a> </td>
                <td><a href="meal?action=delete&userId=${meal.getId()}" > delete </a> </td>
        </tr>
        </c:forEach>
    </table>


</body>
</html>
