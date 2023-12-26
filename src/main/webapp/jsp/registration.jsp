<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            box-sizing: border-box;
        }

        button {
            background-color: #4caf50;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
        .radio-group {
            display: flex;
            align-items: center; /* Выравнивание по центру */
        }

        .radio-label {
            margin-right: 15px; /* Отступ между радиокнопкой и текстом */
            display: flex;
            align-items: center; /* Выравнивание по центру */
        }
    </style>
</head>
<body>

<form action="/registration" method="post">
    <label for="name">User name:</label>
    <input type="text" id="name" name="name" required>

    <label for="birthday">Date of birthday:</label>
    <input type="date" name="birthday" id="birthday">
    <c:if test="${not empty requestScope.duplicateError}">
        <div style="color:red">
            ${requestScope.duplicateError}
        </div>
    </c:if>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>

    <select name="selectedRole">
        <c:forEach var="role" items="${requestScope.roles}">
            <option value="${role}">${role}</option>
        </c:forEach>
    </select>
    <br>
    <br>
    <div class="radio-group">
        <c:forEach var="gender" items="${requestScope.genders}">
            <div class="radio-label">
                <input type="radio" name="gender" value="${gender}">
                    ${gender}
            </div>
        </c:forEach>
    </div>
    <br>
    <br>
    <button type="submit">Send</button>
</form>
<c:if test="${not empty requestScope.errors}">
    <div style="color:red">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error.message}</span>
        </c:forEach>
    </div>
</c:if>

</body>
</html>
