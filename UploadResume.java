package com.pkg.falcon;

import com.dbcon.DbCon;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/uploadres")
@MultipartConfig

public class UploadResume extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String file_n = null;
        PrintWriter out = res.getWriter();


        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("session_email");
        Connection con = null;
        Part pr = req.getPart("res");
         file_n = pr.getSubmittedFileName();
        String dir = getServletContext().getRealPath("") + "images";
        System.out.println(dir);

        try {
            con = DbCon.getConnect();

            PreparedStatement ps1 = con.prepareStatement("select * from resume where email=?");
            ps1.setString(1, email);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                //update
                PreparedStatement ps2 = con.prepareStatement("update resume set rpath=? where email=?");
                ps2.setString(1, file_n);
                ps2.setString(2, email);

                int i = ps2.executeUpdate();
                if (i > 0) {


                    File fil = new File(dir);
                    System.out.println(dir);


                    pr.write(dir + File.separator + file_n);

                    res.sendRedirect("Profile.jsp");
                } else {


                    RequestDispatcher rd1 = req.getRequestDispatcher("error.jsp");
                    rd1.include(req, res);

                    RequestDispatcher rd2 = req.getRequestDispatcher("upload-resume.jsp");
                    rd2.include(req, res);
                }
            } else {
                //insert
                PreparedStatement ps3 = con.prepareStatement("insert into resume(email, rpath) values(?,?)");
                ps3.setString(1, email);
                ps3.setString(2, file_n);

                int i = ps3.executeUpdate();
                if (i > 0) {
                    out.println("upload success");



                    File fil = new File(dir);



                    pr.write(dir + File.separator + file_n);


                    res.sendRedirect("Profile.jsp");
                } else {


                    RequestDispatcher rd1 = req.getRequestDispatcher("error.jsp");
                    rd1.include(req, res);

                    RequestDispatcher rd2 = req.getRequestDispatcher("upload-resume.jsp");
                    rd2.include(req, res);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher rd1=req.getRequestDispatcher("error.jsp");
            rd1.include(req, res);

            RequestDispatcher rd2=req.getRequestDispatcher("upload-resume.jsp");
            rd2.include(req, res);
        }


    }
}