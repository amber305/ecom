package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    // Hardcoded user credentials for demo
    private static final String DEMO_USERNAME = "ashish";
    private static final String DEMO_PASSWORD = "password123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Show login form on GET request
        req.getRequestDispatcher("account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Process login form submission
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (DEMO_USERNAME.equals(username) && DEMO_PASSWORD.equals(password)) {
            // Login successful, create session and redirect
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect("project.html");  // redirect to homepage or dashboard
        } else {
            // Login failed, show error
            req.setAttribute("errorMessage", "Invalid username or password");
            req.getRequestDispatcher("account.jsp").forward(req, resp);
        }
    }
}
