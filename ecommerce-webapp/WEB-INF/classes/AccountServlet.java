package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    // Admin credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Check if user is already logged in
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            resp.sendRedirect("project.html");
            return;
        }
        // Show login form on GET request
        req.getRequestDispatcher("account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Process login form submission
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            // Login successful, create session and redirect
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("isAdmin", true);
            resp.sendRedirect("project.html");
        } else {
            // Login failed, show error
            req.setAttribute("errorMessage", "Invalid username or password. Please use admin/admin to login.");
            req.getRequestDispatcher("account.jsp").forward(req, resp);
        }
    }
} 