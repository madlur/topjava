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

<form method="POST" action='meals' name="editMeal">
    <table>
        <tr>
            <td> DateTime:</td>
            <td>
                <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTimeMeal"
                               type="both"/>
                <fmt:formatDate var="fmtDate" pattern="yyyy-MM-dd HH:mm" value="${parsedDateTimeMeal}"/>
                <input type="datetime-local" name="dateTime" value="${fmtDate}">
            </td>
        </tr>
        <tr>
            <td><label>Description:</label></td>
            <td><input type="text" value="<c:out value="${meal.description}"/>" name="description" required></td>
        </tr>
        <tr>
            <td><label>Calories:</label></td>
            <td><input type="text" value="<c:out value="${meal.calories}"/>" name="calories" required></td>
        </tr>
        <td hidden="true"><input type="number" value="<c:out value="${mealToId}"/>" name="id"></td>
    </table>
    <input type="submit" value="Save"/>
    <a href="meals?action=listMeal"><input type="button" value="Cancel"/></a>
</form>
</body>
</html>
