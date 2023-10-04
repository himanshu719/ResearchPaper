package com.pkg.falcon;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/Logout")

public class Logout extends HttpServlet
	{
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	    {
			Cookie[] ck=req.getCookies();
			for(Cookie cookie : ck)
			{
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
			}
	        HttpSession session=req.getSession();
	        session.invalidate();
	        
	        resp.sendRedirect("login.jsp");
	    }
	}


