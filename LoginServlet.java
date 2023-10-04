package com.pkg.falcon;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.dbcon.DbCon;



@WebServlet("/log")

public class LoginServlet extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();
        
        String email2=req.getParameter("email1");
        String pass2=req.getParameter("pass1");
        String remme2=req.getParameter("rememberme1");
        String name2="", gender2="", city2="", field2="";
        String about2,skills2;

        Connection con = null;
        
        try
        {
            
            con=DbCon.getConnect();
            
            PreparedStatement ps=con.prepareStatement("select * from register where email=? and password=?");
            ps.setString(1, email2);
            ps.setString(2, pass2);
       
         
             ResultSet rs=ps.executeQuery();
             
//             PreparedStatement pabout=con.prepareStatement("select about,skills from about_user where email=?");
//             pabout.setString(1, email2);
//
//            ResultSet rs2= pabout.executeQuery();
            
            if(rs.next())
            	
            {
                if(remme2==null || remme2.equals("null"))
                {
                    remme2="";
                }
                if(remme2.equals("remember-me"))
                {
                    System.out.println("Cookie set");
                    Cookie ck1=new Cookie("cookie_email", email2);
                    ck1.setMaxAge(60*60*24*365);
                    resp.addCookie(ck1);

                    Cookie ck2=new Cookie("cookie_status", "true");
                    ck2.setMaxAge(60*60*24*365);
                    resp.addCookie(ck2);
                }
//            	 if(rs2.next()) {
//              	   about2= rs2.getString("about");
//              	  skills2= rs2.getString("skills");
//              	  HttpSession session2=req.getSession();
//                    session2.setAttribute("session_title", about2);
//                    session2.setAttribute("session_skills", skills2);
//              	  }
                RequestDispatcher rd=req.getRequestDispatcher("GetUserData");
                rd.include(req, resp);
              
                 resp.sendRedirect("Profile.jsp");
            }
            
            else
            {
                //resp.sendRedirect("login.jsp"); 
                RequestDispatcher rd1=req.getRequestDispatcher("header.jsp");
                rd1.include(req, resp);
                
                RequestDispatcher rd2=req.getRequestDispatcher("menubar.jsp");
                rd2.include(req, resp);
                
                RequestDispatcher rd3=req.getRequestDispatcher("loginerror.jsp");
                rd3.include(req, resp);
                
                RequestDispatcher rd4=req.getRequestDispatcher("logindiv.jsp");
                rd4.include(req, resp);
                
                RequestDispatcher rd5=req.getRequestDispatcher("footer.jsp");
                rd5.include(req, resp);
            }
            
           
       
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
	}


