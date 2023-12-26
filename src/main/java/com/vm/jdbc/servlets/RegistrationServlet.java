package com.vm.jdbc.servlets;

import com.vm.jdbc.dto.CreateUserDto;
import com.vm.jdbc.entity.Gender;
import com.vm.jdbc.entity.Role;
import com.vm.jdbc.exceptions.ValidationException;
import com.vm.jdbc.service.UserService;
import com.vm.jdbc.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static com.vm.jdbc.utils.UrlPath.REGISTRATION;

@WebServlet(REGISTRATION)
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());
        req.setAttribute("genders", Gender.values());
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Email from form: " + req.getParameter("email"));
        System.out.println("Password from form: " + req.getParameter("password"));
        var createUserDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .birthday(req.getParameter("birthday"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .role(req.getParameter("selectedRole"))
                .gender((req.getParameter("gender")))
                .build();
        System.out.println("Email in CreateUserDto: " + createUserDto.getEmail());
        System.out.println("Password in CreateUserDto: " + createUserDto.getPassword());
        try {
            userService.create(createUserDto);
            resp.sendRedirect("/login");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            e.printStackTrace();
            doGet(req, resp);
        } catch (RuntimeException e) {
            if (isDuplicateKeyError(e.getCause())) {
                req.setAttribute("duplicateError", "Email already exists. Please use a different email.");
                doGet(req, resp);
            }
        }
    }

    private boolean isDuplicateKeyError(Throwable e) {
        if (e instanceof SQLException) {
            return "23505".equals(((SQLException) e).getSQLState());
        }
        return false;
    }
}