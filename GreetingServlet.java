package com.pkg.falcon;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/greet") // Use the @WebServlet annotation to define the servlet mapping
public class GreetingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the user's name from the request parameter
        String name = request.getParameter("name");

        // Create a greeting message
        String greeting = "Hello, " + name + "!";

        // Set the greeting message as an attribute in the request
        request.setAttribute("greeting", greeting);

        // Forward the request to the JSP for displaying the greeting
        request.getRequestDispatcher("/greet.jsp").forward(request, response);
    }
}

