<%@ page import="ru.javawebinar.topjava.util.MealsUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Meals list</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="">Add Meal</a>
<p>

<table border="1">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:set var = "counter" value="0" scope="page"/>
    <c:forEach var="meal" items="${meals}">
        <tr style="color:${meal.excess ? 'red' : 'black'}">
            <td>
                <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                               type="both"/>
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/>
            </td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><a href="meals?update=<c:out value = "${counter}"/>">Update</a></td>
            <td><a href="meals?delete=<c:out value = "${counter}"/>">Delete</a></td>
        <c:set var="counter" value="${counter + 1}" scope="page"/>
        </tr>
    </c:forEach>
</table>

</body>
</html>
