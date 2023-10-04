package com.pkg.falcon;

import com.dbcon.DbCon;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/contact")
public class Contact extends HttpServlet {
    protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String name1=req.getParameter("name1");
        String email1=req.getParameter("email1");
        String subject1=req.getParameter("subject1");
        String message1=req.getParameter("message1");
        String from = "himanshubishnoi432@gmail.com";

        Date d=new Date();

        SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
        String date1=sdf1.format(d);

        SimpleDateFormat sdf2=new SimpleDateFormat("HH:mm:ss");
        String time1=sdf2.format(d);

        Connection con=null;
        try
        {
            con= DbCon.getConnect();
            con.setAutoCommit(false);

            PreparedStatement ps=con.prepareStatement("insert into contact_us(name, email, subject, message, date1, time1) values(?,?,?,?,?,?)");
            ps.setString(1, name1);
            ps.setString(2, email1);
            ps.setString(3, subject1);
            ps.setString(4, message1);
            ps.setString(5, date1);
            ps.setString(6, time1);

            int i=ps.executeUpdate();
            if(i>0)
            {
                con.commit();
                String subject11="Thank you"+ " " +name1;
                String message11 = "Thank you for contact in Falcon. Our team will contact you as soon as possible regarding your inquiry on '" +
                        subject1 + "'.\n\n" + message1;

               boolean b= SendMail.sendEmail(email1,from,subject11, message11);
               if(b){
                   System.out.println("email sent");
               }
               else {
                   System.out.println("not sent");
               }

                resp.sendRedirect("contact-us.jsp");

//                RequestDispatcher rd1=req.getRequestDispatcher("success.jsp");
//                rd1.include(req, resp);
//
//                RequestDispatcher rd2=req.getRequestDispatcher("contact-us.jsp");
//                rd2.include(req, resp);
            }
            else
            {
                con.rollback();

                resp.sendRedirect("contact-us.jsp");

//                RequestDispatcher rd1=req.getRequestDispatcher("error.jsp");
//                rd1.include(req, resp);
//
//                RequestDispatcher rd2=req.getRequestDispatcher("contact-us.jsp");
//                rd2.include(req, resp);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();

            try
            {
                con.rollback();

                resp.sendRedirect("contact-us.jsp");

//                RequestDispatcher rd1=req.getRequestDispatcher("error.jsp");
//                rd1.include(req, resp);
//
//                RequestDispatcher rd2=req.getRequestDispatcher("contact-us.jsp");
//                rd2.include(req, resp);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        finally
        {
            try
            {
                con.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doService(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doService(req, resp);
    }
}
