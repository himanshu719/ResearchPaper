package com.pkg.falcon;

import com.dbcon.DbCon;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/location-search")
public class LocationSearch extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, IOException {
        PrintWriter out=resp.getWriter();

        String location=req.getParameter("location");

        String id,job_profile, company, experience, description, date1, time1;
        Connection con=null;
        try
        {
            con= DbCon.getConnect();

            PreparedStatement ps=con.prepareStatement("select * from jobs where location LIKE '%"+location+"%'");
            //ps.setString(1, tech1);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                id=rs.getString("id");
                job_profile=rs.getString("job_profile");
                company=rs.getString("company");
                experience=rs.getString("experience");
                description=rs.getString("description");
                date1=rs.getString("date1");
                time1=rs.getString("time1");

                out.print("<div class=\"col-md-12 display_job_div\">\n" +
                        "                                <b>"+job_profile+"</b> <span style=\"font-size: 12px; color: #9f9d9d; float: right\"> ("+date1+" & "+time1+")</span> <br>\n" +
                        "                            <span class=\"fa fa-home\"></span> <span style=\"color: #9f9d9d\"> Company : </span> "+company+"</span> <br>\n" +
                        "                                <span> <span class=\"fa fa-book\"></span> <span style=\"color: #9f9d9d\"> Experience : </span> "+experience+"</span> <br>\n" +
                        "                                <span> <span class=\"fa fa-file\"></span>  <span style=\"color: #9f9d9d\"> Job Description : </span> "+description+"</span> <br>\n" +
                        "                                <br><a href=\"job-description.jsp?jid="+id+"\"> See Full Details </a>\n" +
                        "                            </div>");
            }
        }
        catch(Exception e)
        {
            out.print(e);
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

