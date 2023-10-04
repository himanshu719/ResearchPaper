package com.pkg.falcon;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.dbcon.DbCon;
import jakarta.servlet.http.HttpSession;


@WebServlet("/Reg")
public class RegisterServlet extends HttpServlet {

       
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  resp.setContentType("text/html");
	        PrintWriter out=resp.getWriter();
	        
	        String name2=req.getParameter("name1");
	        String email2=req.getParameter("email1");
	        String pass2=req.getParameter("pass1");
	        String gender2=req.getParameter("gender1");
	        String[] field2=req.getParameterValues("field1");
	        String city2=req.getParameter("city1");
	        
	        String fields2="";
	        if(field2!=null)
	        {
	            for(String s:field2)
	            {
	                fields2=fields2+","+s;
	            }
	        }
	        
	        try
	        {
	           
	            Connection con=DbCon.getConnect();
	            
	            PreparedStatement ps=con.prepareStatement("insert into register(name, email, password, gender, field, city) values(?,?,?,?,?,?)");
	      
	          
	            ps.setString(1, name2);
	            ps.setString(2, email2);
	            ps.setString(3, pass2);
	            ps.setString(4, gender2);
	            ps.setString(5, fields2);
	            ps.setString(6, city2);

	            int j=ps.executeUpdate();
	        
	            
	            PreparedStatement ps2=con.prepareStatement("insert into about_user(email, about, skills) values(?,?,?)");
	            ps2.setString(1, email2);
	            ps2.setString(2, "");
	            ps2.setString(3, "");
	            int i2=ps2.executeUpdate();

				PreparedStatement ps3=con.prepareStatement("insert into profile_pics(email, path) values(?,?)");
				ps3.setString(1, email2);
				ps3.setString(2, "defaultprofile.png");
				int i3=ps3.executeUpdate();
				System.out.println(i3);
	            
	          
		          
	          
	            if(j>0 && i2>0 && i3>0)
	            {
					HttpSession session=req.getSession();
					session.setAttribute("session_pic", "defaultprofile.png");
	                resp.sendRedirect("login.jsp");
	            }
	            else
	            {
	                out.println("Failed");
	               
	            }
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
		
		
	}

}
