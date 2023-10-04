package com.pkg.falcon;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.dbcon.DbCon;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/del")
public class DeleteEducation extends HttpServlet {
	protected void doPost(HttpServletRequest req,HttpServletResponse res) {
		String id2=req.getParameter("id1");
		Connection conD=null;
		   PreparedStatement psD=null;
		
		try {
			
           conD= DbCon.getConnect();
           
          psD= conD.prepareStatement("delete from education where id=?");
            psD.setString(1, id2);
            int i=psD.executeUpdate();
            if(i>0) {
            	
            	res.sendRedirect("Profile.jsp");
            }
            else {
            	res.sendRedirect("edit-educationdetail.jsp");
            }
		}
		catch(Exception e)
        {
            try
            {
                conD.rollback();
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
                psD.close();
                conD.close();
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
		
	}
}
	


