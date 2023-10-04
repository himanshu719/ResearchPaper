package com.pkg.falcon;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.dbcon.DbCon;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/update")
public class EditAboutProfile extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		 HttpSession session1=req.getSession();
		 PrintWriter out=res.getWriter();
	        String email2=(String)session1.getAttribute("session_email");
	        
		    String name2=req.getParameter("name1");
	        String city2=req.getParameter("city1");
	        String gender2=req.getParameter("gender1");
	        String title2=req.getParameter("title1");
	        String skills2=req.getParameter("skills1");
	        Connection con=null;
	        PreparedStatement ps1=null;
	        try
	        {
	           
	          con= DbCon.getConnect();
	         
	           
	            
	           ps1= con.prepareStatement("update register set name=?, city=?, gender=? where email=?");
	            ps1.setString(1, name2);
	            ps1.setString(2, city2);
	            ps1.setString(3, gender2);
	            ps1.setString(4, email2);
	            int i1=ps1.executeUpdate();
	            
	            
	            PreparedStatement ps2=con.prepareStatement("update about_user set about=?, skills=? where email=?");
	            ps2.setString(1, title2);
	            ps2.setString(2, skills2);
	            ps2.setString(3, email2);
	            int i2=ps2.executeUpdate();
	            
	            if(i1>0 && i2>0)
	            {
	            	
	                session1.setAttribute("session_name", name2);
	                session1.setAttribute("session_email", email2);
	                session1.setAttribute("session_gender", gender2);
	                session1.setAttribute("session_city", city2);
	                
	                session1.setAttribute("session_title", title2);
	                session1.setAttribute("session_skills", skills2);
	                
	                res.sendRedirect("Profile.jsp");
	            }
	            else
	            {
	            	   RequestDispatcher rd=req.getRequestDispatcher("error.jsp");
	                   rd.include(req, res);
	                   
	                   RequestDispatcher rd2=req.getRequestDispatcher("edit-profile-about.jsp");
	                   rd2.include(req, res);
	             
	            }
	        }
	        catch(Exception e)
	        {
	            try
	            {
	                con.rollback();
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
	                ps1.close();
	                con.close();
	            }
	            catch(Exception ee)
	            {
	                ee.printStackTrace();
	            }
	        }	}

}
