// package com.example.demo.servlet;

// import jakarta.servlet.http.*;
// import jakarta.servlet.annotation.WebServlet;
// import java.io.IOException;

// @WebServlet("/status")
// public class SimpleStatusServlet extends HttpServlet {
//     @Override
//     public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException { 
//         resp.setContentType("text/plain");
//         resp.getWriter().write("SimpleStatusServlet OK");
//     }
// }
package com.example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SimpleStatusServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("SimpleStatusServlet is running");
        out.flush();
    }
}