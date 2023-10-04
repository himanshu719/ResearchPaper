package com.pkg.falcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dbcon.DbCon;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;




public class GetEduDetail extends HttpServlet {
	protected void doGet(HttpServletResponse res,HttpServletRequest req) {
		HttpSession session=req.getSession();
		 String email2=(String)session.getAttribute("session_email");
		try {
			 
	            Connection con=DbCon.getConnect();
	           PreparedStatement psG=con.prepareStatement("select *from education where email=?");
	           psG.setString(1, email2);
               ResultSet rs=psG.executeQuery();
               while(rs.next()) {
            	   String school = rs.getString("school");
            	   session.setAttribute("session_school", school);
                   String degree = rs.getString("degree");
                   session.setAttribute("session_degree", degree);
                   String grade = rs.getString("grade");
                   session.setAttribute("session_grade", grade);
                   String year = rs.getString("year");
                   session.setAttribute("session_year", year);
            	  
               }
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
