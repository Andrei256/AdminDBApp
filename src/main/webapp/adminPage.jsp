<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin</title>
</head>
<body>

<div align="center">
    <h2>Hello, ${user.name}</h2>
    <h3>Registered users:</h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Login</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${usersList}">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.name}" /></td>
                <td><c:out value="${user.login}" /></td>
                <td>
     <form action="/delete" method="POST">
     <input type="hidden" name="id" value="${user.id}">
     <input type="submit" value="Удалить">
     </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>


