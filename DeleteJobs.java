package com.pkg.falcon;

import com.dbcon.DbCon;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/j_delete")
public class DeleteJobs extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session=req.getSession();
        String email=(String) session.getAttribute("session_email");

        String jid=req.getParameter("jobid");

        Connection con=null;
        try
        {
            con= DbCon.getConnect();
            con.setAutoCommit(false);

            PreparedStatement ps=con.prepareStatement("delete from applied_jobs where email=? and jobid=?");
            ps.setString(1, email);
            ps.setString(2, jid);
            int i=ps.executeUpdate();
            if(i>0)
            {
                con.commit();

                resp.sendRedirect("my-applied-jobs.jsp");
            }
            else
            {
                con.rollback();

                RequestDispatcher rd1=req.getRequestDispatcher("error.jsp");
                rd1.include(req, resp);
                RequestDispatcher rd2=req.getRequestDispatcher("job-des.jsp?jid="+jid);
                rd2.include(req, resp);
            }
        }
        catch(Exception e)
        {
            try
            {
                con.rollback();

                RequestDispatcher rd1=req.getRequestDispatcher("error.jsp");
                rd1.include(req, resp);
                RequestDispatcher rd2=req.getRequestDispatcher("job-des.jsp?jid="+jid);
                rd2.include(req, resp);
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
