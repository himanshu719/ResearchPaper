package com.pkg.falcon;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CheckCookie")
public class CheckCookie extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException, IOException {
        String email="";
        Cookie[] ck=req.getCookies();
        if(ck != null)
        {
            for(Cookie cookie : ck)
            {
                if(cookie.getName().equals("cookie_email"))
                {
                    email=cookie.getValue();

                    req.setAttribute("email1", email);

                    RequestDispatcher rd=req.getRequestDispatcher("GetUserData");
                    rd.include(req, resp);
                }
            }
        }
        else
        {
            resp.sendRedirect("index.jsp");
        }
        System.out.println("i am on CheckCookie page : "+email);
    }
}
