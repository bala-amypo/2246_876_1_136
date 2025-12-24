package com.example.demo.servlet;
import jakarta.servlet.http.*;
import java.io.IOException;

public class SimpleStatusServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // MUST BE PUBLIC (or test must be in same package) 
        // to avoid "has protected access" error
        resp.getWriter().write("OK");
    }
}