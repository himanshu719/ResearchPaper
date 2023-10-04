package com.pkg.falcon;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbcon.DbCon;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddEdu")
public class AddEducationDetails  extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException {
		 HttpSession session=req.getSession();
	        String email2=(String)session.getAttribute("session_email");
	        
		String school2 =req.getParameter("school1");
		String degree2=req.getParameter("degree1");
	    String	year2=req.getParameter("year1");
		String grade2=req.getParameter("grade1");
		String description2=req.getParameter("description1");
		 Connection conE= null;
	        PreparedStatement psE = null;
		
		try {
			
			
           conE= DbCon.getConnect();
          
           psE= conE.prepareStatement("insert into education(email, school, degree, year, grade, description) values(?,?,?,?,?,?)");
            psE.setString(1, email2);
            psE.setString(2, school2);
            psE.setString(3, degree2);
            psE.setString(4, year2);
            psE.setString(5, grade2);
            psE.setString(6, description2);
            int i=psE.executeUpdate();
            if(i>0)
            {
            	
                res.sendRedirect("Profile.jsp");
            }
            else {
            	 RequestDispatcher rd1=req.getRequestDispatcher("error.jsp");
                 rd1.include(req, res);
                 
                 RequestDispatcher rd2=req.getRequestDispatcher("edit-education-profile.jsp");
                 rd2.include(req, res);
            	
            }
			
		}
		catch(Exception e)
        {
            try
            {
                conE.rollback();
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
                psE.close();
                conE.close();
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
		
	
          
         
        
	}

}
