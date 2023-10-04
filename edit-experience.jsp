

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.dbcon.DbCon"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:declaration>
    Connection con=null;
    String company="", location="", year="", job_title="", description="", id="";
</jsp:declaration>

<jsp:scriptlet>
      String pic = (String) session.getAttribute("ses_img");
    String name = (String) session.getAttribute("session_name");

    
    id=request.getParameter("id1");
    
    try
    {

      
        Connection con=DbCon.getConnect();
        
        PreparedStatement ps=con.prepareStatement("select * from experience where id=?");
        ps.setString(1, id);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            company=rs.getString("company");
            location=rs.getString("location");
            year=rs.getString("year");
            job_title=rs.getString("job_title");
            description=rs.getString("description");
        }
    }
    catch(Exception e)
    {
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
</jsp:scriptlet>

<jsp:scriptlet>
    if (name == null || name.equals("")) {
        response.sendRedirect("login.jsp");
    }
</jsp:scriptlet>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Edit Experience </title>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>

        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <script>
        function chgExp(vale){
        	if(vale=='upexp'){
        		document.expform.action="upexp";
        		document.expform.submit();
        	}
        	if(vale=='delexp'){
        		document.expform.action="delexp";
        		document.expform.submit();
        	}
        }
        
        </script>
    </head>
    <body>
        <jsp:include page="profileheader.jsp" />
        <jsp:include page="menubar.jsp" />

        <div class="row">
            <div class="col-md-2"> </div>
            <div class="col-md-8">
                <div class="row" style="border: 1px solid gray; box-shadow: 0px 0px 2px gray; border-radius: 3px">
                    <div class="col-md-2">
                        <img src="images/<jsp:expression>pic</jsp:expression>" height="100"/>
                    </div>
                    <div class="col-md-10">
                        <h2> Edit Experience Details </h2> <br>
                        <b> <jsp:expression>name</jsp:expression> </b>(<jsp:expression> session.getAttribute("session_email") </jsp:expression>) <br> <br>
                        <form name="expform" method="POST">
                                <input type="hidden" value="<jsp:expression>id</jsp:expression>" name="id1" />
                            Company : <br> <input type="text" name="company1" class="textfield_design" value="<jsp:expression>company</jsp:expression>"/> <br> <br>
                            Location : <br> <input type="text" name="location1" placeholder="Eg. delhi" class="textfield_design" value="<jsp:expression>location</jsp:expression>"/> <br> <br>
                            Year : <br> <input type="text" name="year1" placeholder="Eg. 2004-2005" class="textfield_design" value="<jsp:expression>year</jsp:expression>"/> <br> <br>
                            Job Title : <br> <input type="text" name="job_title1" placeholder="Eg. Sr. Java Developer" class="textfield_design" value="<jsp:expression>job_title</jsp:expression>"/> <br> <br>
                            Description : <br> <textarea placeholder="Your certifications, Activities and societies" name="description1" style="padding: 5px 10px; width: 300px; height: 100px; border-radius: 5px; border: 1px solid gray;"><jsp:expression>description</jsp:expression></textarea> <br> <br>
                            <input type="submit" value="Update Experience" class="btn btn-danger" onclick="chgExp('upexp')"/> &nbsp&nbsp<input type="submit" value="Delete Experience" class="btn btn-danger" onclick="chgExp('delexp')"/>
                        </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-2"> </div>
            </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
