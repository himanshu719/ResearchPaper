package com.pkg.falcon;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dbcon.DbCon;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/upexp")
public class EditExp extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		 PrintWriter out=res.getWriter();
	        
	        String id2=req.getParameter("id1");
	        String company2=req.getParameter("company1");
	        String location2=req.getParameter("location1");
	        String year2=req.getParameter("year1");
	        String job_title2=req.getParameter("job_title1");
	        String description2=req.getParameter("description1");
	        Connection con=null;
	        PreparedStatement ps=null;

       		try {
       		 
       	        
       	     con=  DbCon.getConnect();
       	        
         	ps= con.prepareStatement("update experience set company=?, location=?, year=?, job_title=?, description=? where id=?");
       	     ps.setString(1, company2);
             ps.setString(2, location2);
             ps.setString(3, year2);
             ps.setString(4, job_title2);
             ps.setString(5, description2);
             ps.setString(6, id2);
             
             int i=ps.executeUpdate();
             if(i>0)
             {
            	
                 res.sendRedirect("Profile.jsp");
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
                    ps.close();
                    con.close();
                }
                catch(Exception ee)
                {
                    ee.printStackTrace();
                }
            }

}
}
