<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Логирование</title>
<style>
    body {
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    form {
        width: 300px;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    label {
        display: block;
        margin-bottom: 10px;
    }

    input {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
        box-sizing: border-box;
    }

    button {
        background-color: #4CAF50;
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    button:hover {
        background-color: #45a049;
    }

    a {
        text-decoration: none;
    }

    a button {
        background-color: #008CBA;
        margin-top: 10px;
    }

    a button:hover {
        background-color: #007B9E;
    }

    .error-message {
        color: red;
        margin-top: 10px;
    }

</style>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="email">Email:
        <input type="text" name="email" id="email" value="${param.email}" required>
    </label><br>
    <label for="password">Password:
        <input type="text" name="password" id="password" value="${param.password}" required>
        </label><br>
    <button type="submit">Login</button>
    <a href="${pageContext.request.contextPath}/registration">
        <button type="button">Register</button>
    </a>
    <c:if test="${param.error !=null}">
        <div style="color: red">
            <span>Email or password is not correct</span>
        </div>
    </c:if>
</form>
</body>
</html>
