package com.vm.jdbc.servlets;

import com.vm.jdbc.service.TicketService;
import com.vm.jdbc.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        int flightId = Integer.valueOf(req.getParameter("flightId"));
//        var writer = resp.getWriter();
//        writer.write("<h2>List of tickets</h2>");
//        writer.write("<ul>");
//        ticketService
//                .findAllByFlightId(flightId)
//                .stream()
//                .forEach(ticketDto -> writer.write("""
//                        <li>
//                        place %s,passenger %s
//                        </li>
//                        """.formatted(ticketDto.seat(),ticketDto.name())));
//        writer.write("</ul>");
        req.setAttribute("tick", ticketService.findAllByFlightId(flightId));
        req.getRequestDispatcher(JspHelper.getPath("ticket")).forward(req, resp);
    }
}

