package com.pkg.falcon;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/Reg")
public class ValidationFilter implements Filter {

	@Override
	public void init(FilterConfig filconf) throws ServletException {
		
	}
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException {
		  String name2=req.getParameter("name1");
	        String email2=req.getParameter("email1");
	        String pass2=req.getParameter("pass1");
	        String gender2=req.getParameter("gender1");
	        String city2=req.getParameter("city1");
	        ValidationServerSide vs=new ValidationServerSide();
	        if(!vs.nameValidate(name2)) {
	        	 RequestDispatcher rd1=req.getRequestDispatcher("register.jsp");
	             rd1.include(req, res);
	        	
	        }
	        else if(!vs.emailValidate(email2)) {
	        	 RequestDispatcher rd1=req.getRequestDispatcher("register.jsp");
	             rd1.include(req, res);
	        	
	        	
	        }
	        else if(!vs.passwordValidate(pass2)) {
	        	 RequestDispatcher rd1=req.getRequestDispatcher("register.jsp");
	             rd1.include(req, res);
	        	
	        	
	        }
	        else if(gender2==null || gender2.equals(""))
	        {
	            RequestDispatcher rd1=req.getRequestDispatcher("register.jsp");
	            rd1.include(req, res);
	        }
	        else if(city2==null || city2.equals(""))
	        {
	            RequestDispatcher rd1=req.getRequestDispatcher("register.jsp");
	            rd1.include(req, res);
	        }
	        else
	        {
	            chain.doFilter(req, res);
	        }
		
		
	}
	@Override
	public void destroy() {
		
	}

}
