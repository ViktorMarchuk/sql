package com.vm.jdbc.servlets;

import com.vm.jdbc.service.FlightService;
import com.vm.jdbc.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {
    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        try (var writer = resp.getWriter()) {
//            writer.write("<h3>List of flight:</h3>");
//            writer.write("<ul>");
//            flightService.findAll().stream().forEach(flightDto -> writer.write("""
//                     <li>
//                     <a href='/tickets?flightId=%d'>%s</a>
//                     </li>
//                    """.formatted(flightDto.id(), flightDto.description())));
//            writer.write("</ul>");
//        }
        req.setAttribute("flight", flightService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("flight")).forward(req, resp);
    }
}
