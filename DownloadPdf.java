package com.pkg.falcon;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/pdf")
public class DownloadPdf extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("session_email");
        if (email == null || email.isEmpty()) {
            // Handle the case where the user is not logged in or email is missing
            resp.sendRedirect("login.jsp"); // Redirect to a login page or appropriate error page
            return;
        }

        String b_path = getServletContext().getRealPath("") + File.separator + "resume";
        String pdfName = email + ".pdf";
        String pdfPath = b_path + File.separator + pdfName;

        File file = new File(pdfPath);
        if (file.exists() && file.isFile()) {
            // Set the response headers for file download
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + pdfName + "\"");
            resp.setContentType("application/pdf");

            // Create an input stream to read the PDF file
            try (FileInputStream fis = new FileInputStream(file)) {
                // Get the output stream to write the PDF to the response
                try (OutputStream out = resp.getOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
            }
        } else {


            RequestDispatcher rd3=req.getRequestDispatcher("pdf-error.jsp");
            rd3.include(req, resp);

            RequestDispatcher rd4=req.getRequestDispatcher("Profile.jsp");
            rd4.include(req, resp);

            RequestDispatcher rd5=req.getRequestDispatcher("footer.jsp");
            rd5.include(req, resp);
        }
    }
}
