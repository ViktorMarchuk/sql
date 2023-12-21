package com.vm.jdbc.servlets;

import com.vm.jdbc.service.FlightService;
import com.vm.jdbc.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/content")
public class ContentServlet extends FlightServlet {
    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var flights = flightService.findAll();
        req.setAttribute("flights", flights);
        req.getRequestDispatcher(JspHelper.getPath("content")).forward(req, resp);
    }
}
