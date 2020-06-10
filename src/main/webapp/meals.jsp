<%--
  Created by IntelliJ IDEA.
  User: Denis
  Date: 07.06.2020
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="meal" class="ru.javawebinar.topjava.model.MealTo" scope="request" />


<html>
<head>
    <title>Список еды</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>

    <table border="3">
        <caption> список еды</caption>
        <tr>
            <th> Дата время </th>
            <th> Описание  </th>
            <th> Калории  </th>
        </tr>


        <c:forEach var="meal" items="${requestScope.maleList}">

            <tr>
                <tr style="color:${meal.excess ? "red": "green"}">

                <td><c:out value="${meal.dateTime}"/></td>



                <td><c:out value="${meal.description}" /></td>
                <td><c:out value="${meal.calories}"/></td>
                <td><a href="meal" type="delete" value = "delete" data-method="delete" methods="delete" name="delete">
                    удалить</a> </td>

            </tr>
        </c:forEach>
    </table>


</body>
</html>
