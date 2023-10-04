
<%@page import="com.dbcon.DbCon"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.dbcon.DbCon"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:declaration>
    String school="", degree="", year="", grade="", description="";
</jsp:declaration>

<jsp:scriptlet>
    String name = (String) session.getAttribute("session_name");
    String gender = (String) session.getAttribute("session_gender");
    String city = (String) session.getAttribute("session_city");
    String fields = (String) session.getAttribute("session_field");
    String title = (String) session.getAttribute("session_title");
     String pic = (String) session.getAttribute("ses_img");

    if (title == null || title.equals("")) {
        title = "";
    }
    String skills = (String) session.getAttribute("session_skills");
    if (skills == null || skills.equals("")) {
        skills = "";
    }
    
    String id=request.getParameter("id");
    
    try
    {
        Connection con=DbCon.getConnect();
        
        PreparedStatement ps=con.prepareStatement("select * from education where id=?");
        ps.setString(1, id);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            school=rs.getString("school");
            degree=rs.getString("degree");
            year=rs.getString("year");
            grade=rs.getString("grade");
            description=rs.getString("description");
        }
    }
    catch(Exception e)
    {
        out.print(e);
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
        <title>  edit education- Profile </title>

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>

        <link rel="stylesheet" type="text/css" href="css/style.css" />

        <script>
            function changeGender()
            {
                document.getElementById('chnggendertf').value = document.getElementById('chnggender').value;
            }
            function changeCity()
            {
                document.getElementById('chngcitytf').value = document.getElementById('chngcity').value;
            }
            function makeUpdate(val){
            	if(val=='update'){
            		document.eduregform.action="edit_detail"
            		document.eduregform.submit();
            	}
            	if(val=='delete'){
            		document.eduregform.action="del"
                		document.eduregform.submit();
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
                        <h2> Edit Educational Details </h2>   <br>
                        <b> <jsp:expression>name</jsp:expression> </b>(<jsp:expression> session.getAttribute("session_email") </jsp:expression>) <br> <br>
                        <form name="eduregform" method="POST">
                                <input type="hidden" value="<jsp:expression>id</jsp:expression>" name="id1" />
                            School/College : <br> <input type="text" name="school1" class="textfield_design" value="<jsp:expression>school</jsp:expression>"/> <br> <br>
                            Degree : <br> <input type="text" name="degree1" placeholder="Eg. 10th or B.tech" class="textfield_design" value="<jsp:expression>degree</jsp:expression>"/> <br> <br>
                            Year : <br> <input type="text" name="year1" placeholder="Eg. 2001-2004" class="textfield_design" value="<jsp:expression>year</jsp:expression>"/> <br> <br>
                            Grade : <br> <input type="text" name="grade1" placeholder="Eg. 90" class="textfield_design" value="<jsp:expression>grade</jsp:expression>"/> % <br> <br>
                            Description : <br> <textarea placeholder="Your certifications, Activities and societies" name="description1" style="padding: 5px 10px; width: 300px; height: 100px; border-radius: 5px; border: 1px solid gray;"><jsp:expression>description</jsp:expression></textarea> <br> <br>
                            <input type="submit" value="Update Education" class="btn btn-danger" onclick="makeUpdate('update')" /> <input action="del" class="btn btn-danger" type="submit" value="Delete Education" onclick="makeUpdate('delete')"></input>
                        </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-2"> </div>
            </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
