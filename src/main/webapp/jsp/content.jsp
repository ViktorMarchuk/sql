<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.vm.jdbc.servlets.ContentServlet" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <span>Content Руский</span>
    <p>Size: ${requestScope.flights.size()}</p>
    <p>Show desc.: ${requestScope.flights.get(2).description()}</p>
</div>
</body>
</html>
