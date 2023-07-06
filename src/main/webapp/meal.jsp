<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Edit meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<p>

<h3>Edit meal</h3>
<br/>
<form method="POST" action='meals' name="editMeal">
    <table>
        <c:choose>
            <c:when test="${not empty param.action}">
                <td> DateTime:</td>
                <td>
                    <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTimeMeal"
                                   type="both"/>
                    <fmt:formatDate var="fmtDate" pattern="yyyy-MM-dd HH:mm" value="${parsedDateTimeMeal}"/>
                    <input type="datetime-local" name="dateTime" value="${fmtDate}">
                </td>
            </c:when>
            <c:otherwise>
                <td> DateTime:</td>
                <c:set var="now" value="<%=new java.util.Date()%>"/>
                <td>
                    <fmt:formatDate var="fmtDate" pattern="yyyy-MM-dd HH:mm" value="${now}"/>
                    <input type="datetime-local" name="dateTime" value="${fmtDate}" required>
                </td>
            </c:otherwise>
        </c:choose>
        <tr>
        </tr>
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
