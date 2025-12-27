package com.example.demo.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;

public class HealthCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain");
        resp.getWriter().write("Application is running");
    }
}
