<%@ page import="com.vm.jdbc.service.TicketService" %>
<%@ page import="com.vm.jdbc.dto.TicketDto" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2>List of tickets:</h2>
<ul>
    <%--    <%--%>
    <%--        TicketService ticketService = TicketService.getInstance();--%>
    <%--        int flightId = Integer.valueOf(request.getParameter("flightId"));--%>
    <%--        for (TicketDto ticketDto : ticketService.findAllByFlightId(flightId)) {--%>
    <%--    %>--%>
    <%--    <li>place: <%= ticketDto.seat() %>, passenger: <%= ticketDto.name() %></li>--%>
    <%--    <%--%>
    <%--        }--%>
    <%--    %>--%>
    <c:forEach var="ticket" items="${requestScope.tick}">
        <li>${ticket.seat()}</li>
    </c:forEach>
</ul>
</body>
</html>
