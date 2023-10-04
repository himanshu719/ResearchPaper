package com.pkg.falcon;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

@WebServlet("/download")
public class DownloadResume extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String fileName = (String) session.getAttribute("ses_dw");
        String directory = getServletContext().getRealPath("") + File.separator + "images";


        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        resp.setContentType("application/octet-stream");


        FileInputStream fis = new FileInputStream(directory + File.separator + fileName);


        OutputStream out = resp.getOutputStream();


        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }


        fis.close();
        out.close();
    }
}
