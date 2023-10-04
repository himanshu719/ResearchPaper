package com.pkg.falcon;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.dbcon.DbCon;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/delexp")
public class DelExp extends HttpServlet{
          protected void doPost(HttpServletRequest req,HttpServletResponse res) {
        	  String id2=req.getParameter("id1");
      		Connection conE=null;
      		   PreparedStatement psD=null;
      		
      		try {
      			
                 conE= DbCon.getConnect();
                 
                psD= conE.prepareStatement("delete from experience where id=?");
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
                      psD.close();
                      conE.close();
                  }
                  catch(Exception ee)
                  {
                      ee.printStackTrace();
                  }
              }
      		
          }
}
