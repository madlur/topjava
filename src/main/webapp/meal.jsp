<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTimeMeal"
               type="both"/>
<fmt:formatDate var="fmtDate" pattern="yyyy-MM-dd HH:mm" value="${parsedDateTimeMeal}"/>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<fmt:formatDate var="fmtNowDate" pattern="yyyy-MM-dd HH:mm" value="${now}"/>

<html>
<head>
    <title>Edit meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<p>
    <c:choose>
    <c:when test="${param.action =='update'}">
<h3>Update meal</h3>
</c:when>
<c:otherwise>
    <h3>Add meal</h3>
</c:otherwise>
</c:choose>
<br/>
<form method="POST" action='meals' name="addMeal">
    <table>
        <table>
            <td> DateTime:</td>
            <td>
                <input type="datetime-local" name="dateTime" value="${not empty meal.dateTime ? fmtDate : fmtNowDate}"
                       required>
            </td>
            <tr>
                <td><label>Description:</label></td>
                <td><input type="text" value="${meal.description}" name="description" required></td>
            </tr>
            <tr>
                <td><label>Calories:</label></td>
                <td><input type="number" value="${meal.calories}" name="calories" required></td>
                <td hidden="true"><input type="number" value="${meal.id}" name="id"></td>
            </tr>
        </table>
        <input type="submit" value="Save"/>
        <a href="meals"><input type="button" value="Cancel"/></a>
</form>
</body>
</html>
