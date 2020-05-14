<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form align="center" action="/registration" method="POST">
    <p>Name: <input name="name" ></p>
    <p>Login: <input name="login" ></p>
    <p>Password: <input name="password" ></p>
    <p><input type="radio" name="admin" value="false" checked />User
        <input type="radio" name="admin" value="true" />Admin</p>
    <input type="submit" value="Ok" />
</form>
</body>
</html>
