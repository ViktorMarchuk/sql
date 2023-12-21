<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Flights</title>
</head>
<body>
<h2>List of flights</h2>
<ul>
    <c:forEach var="fly" items="${requestScope.flight}">
        <li>
            <a href="/tickets?flightId=${fly.id()}">${fly.description()}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>

