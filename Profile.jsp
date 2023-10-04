

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.dbcon.DbCon"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:scriptlet>

    String name = (String) session.getAttribute("session_name");
    String gender = (String) session.getAttribute("session_gender");
    String city = (String) session.getAttribute("session_city");
    String fields = (String) session.getAttribute("session_field");
    String title = (String) session.getAttribute("session_title");
    String skills = (String) session.getAttribute("session_skills");
    String path=(String)session.getAttribute("session_pic");

    if (fields == null || fields.equals("")) {
        fields = "--- Not Available ---";
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
        <title> Welcome : <jsp:expression> name</jsp:expression> </title>
        

            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
            <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>

 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

            <link rel="stylesheet" type="text/css" href="css/style.css" />
            <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        </head>
        <body>
        <jsp:scriptlet>
                            String file="";
                            String email=(String)session.getAttribute("session_email");
                            try
                            {

                                Connection cony=DbCon.getConnect();

                                PreparedStatement ps=cony.prepareStatement("select * from profile_pics where email=?");

                                ps.setString(1, email);

                                ResultSet rsy=ps.executeQuery();

                                    while(rsy.next())
                                    {
                                	file=rsy.getString("path");
                                	}
                                	session.setAttribute("ses_img",file);
                            }
                                	 catch(Exception e)
                                     {
                                     out.print(e);
                                        }

                        </jsp:scriptlet>
        
        <jsp:include page="profileheader.jsp" />
        <jsp:include page="menubar.jsp" />


        <div class="row">
            <div class="col-md-2"> </div>
            <div class="col-md-8">
                <div class="row" style="border: 1px solid gray; box-shadow: 0px 0px 2px gray; border-radius: 3px">
                    <div class="col-md-2" style="background-color:#89ccc2">
                    <a href="edit-profile-pic.jsp" style="font-size: 15px; float: right"><span class="fa fa-pencil" ></span></a><br><br>
                        <img src="images/<jsp:expression>file</jsp:expression>" height="100" style="border-radius:15px"/>

                    </div>
                    <div class="col-md-10" style="background-color:#89ccc2">
                        <h3 style="color: blue"> <jsp:expression> name.toUpperCase()</jsp:expression> <a href="edit-profile-about.jsp" style="font-size: 12px; float: right"><i class="fa fa-pencil"></i> </a> </h3>
                        <b> <jsp:expression>title</jsp:expression> </b> <br> <br>
                        <span style="color: #f2faf9"> Skills : </span> <jsp:expression>skills</jsp:expression> <br> <br>
                        <span style="color: #f2faf9"> Gender : </span> <jsp:expression> gender</jsp:expression> <br>
                        <span style="color: #f2faf9"> City : </span> <jsp:expression> city</jsp:expression> <br>
                        <span style="color: #f2faf9"> Fields : </span> <jsp:expression> fields</jsp:expression> <br>
                        </div>
                </div> <br> <br>
                    <div class="row"  style="border: 1px solid gray; box-shadow: 0px 0px 2px gray; border-radius: 3px">
                        
                        <div class="col-md-12">
                            <h4> Education Details <a href="add-education-profile.jsp" style="font-size: 12px; float: right"> <span><i class="fa fa-plus"></i></span> </a> </h4>
                        <jsp:scriptlet>
                            String school="", degree="", grade="", year="",id="";

                            try
                            {
                               
                                Connection con=DbCon.getConnect();
            
                                PreparedStatement ps=con.prepareStatement("select * from education where email=?");

                                ps.setString(1, email);

                                ResultSet rs=ps.executeQuery();

                                while(rs.next())
                                {
                                	id=rs.getString("id");
                                    school=rs.getString("school");
                                    degree=rs.getString("degree");
                                    grade=rs.getString("grade");
                                    year=rs.getString("year");

                        </jsp:scriptlet>
                        
                            
                            <div class="row" style="background-color: #eeecec">
                                <div class="col-md-2"> &nbsp;&nbsp;&nbsp; <img src="images/school.png" height="70" /> </div>
                                <div class="col-md-10">
                                    <a href="edit-education-detail.jsp?id=<jsp:expression>id</jsp:expression>" style="font-size: 12px; float: right"> <span class="fa fa-pencil"></span> </a>
                                    <b> <span class="fa fa-home">&nbsp;</span> <jsp:expression>school</jsp:expression> <br> </b>
                                <span class="fa fa-book"></span> &nbsp; <jsp:expression> degree </jsp:expression> <span style="color: #858585">(<jsp:expression> year </jsp:expression>)</span> <br>
                                <span class="fa fa-file"></span> &nbsp; <jsp:expression> grade </jsp:expression>% <br>
                                </div>
                        </div> <br>
                            
                        <jsp:scriptlet>
                                }
                            }
                            
                            catch(Exception e)
                            {
                                out.print(e);
                            }
                        </jsp:scriptlet>
                        </div>
                    </div> <br> <br>
                     <div class="row"  style="border: 1px solid gray; box-shadow: 0px 0px 2px gray; border-radius: 3px">
                        
                        <div class="col-md-12">
                            <h4> Experience Details <a href="add-experience.jsp" style="font-size: 12px; float: right"> <span class="fa fa-plus"></span> </a> </h4>
                        <jsp:scriptlet>
                            String company1="", location1="", jobtitle1="", year1="", id1="";
                            try
                            {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection conj=DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal","root","root");
            
                                PreparedStatement psj=conj.prepareStatement("select * from experience where email=?");
                                psj.setString(1, email);
                                ResultSet rsj=psj.executeQuery();
                                while(rsj.next())
                                {
                                    id1=rsj.getString("id");
                                    company1=rsj.getString("company");
                                    location1=rsj.getString("location");
                                    jobtitle1=rsj.getString("job_title");
                                    year1=rsj.getString("year");
                        </jsp:scriptlet>
                            
                            <div class="row" style="background-color: #eeecec">
                                <div class="col-md-2"> &nbsp;&nbsp;&nbsp; <img src="images/school.png" height="70" /> </div>
                                <div class="col-md-10">
                                    <a href="edit-experience.jsp?id1=<jsp:expression>id1</jsp:expression>" style="font-size: 12px; float: right"> <span class="fa fa-pencil"></span> </a>
                                    <b> <i class='material-icons'><span class='material-icon' style='font-size:24px'>&nbsp;</span>business_center</i> <jsp:expression>company1</jsp:expression> <br> </b>
                                    <i class='material-icons'> <span class="" style='font-size:24px'></span>place</i>&nbsp; <jsp:expression>location1</jsp:expression> <span style="color: #858585">(<jsp:expression> year1 </jsp:expression>)</span> <br>
                               <i class=''> <span class="fa fa-book"></span></i> &nbsp; <jsp:expression>jobtitle1</jsp:expression> <br>
                                </div>
                        </div> <br>
                            
                        <jsp:scriptlet>
                                }
                            }
                            catch(Exception e)
                            {
                                out.print(e);
                            }
                        </jsp:scriptlet>
                        </div>
                    </div> <br> <br>
                <div class="row"  style="border: 1px solid gray; box-shadow: 0px 0px 2px gray; border-radius: 3px">

                    <div class="col-md-12">
                        <h4> Resume Details </h4>
                        <div class="row" style="background-color: #ececec">
                            <div class="col-md-12">
                                <a href="upload-resume.jsp"> <span class="fa fa-pencil"></span> Upload Resume </a>
                                <jsp:scriptlet>
                            String file_name=null;
                                try
                                {
                                    Connection con=DbCon.getConnect();
                                    PreparedStatement ps=con.prepareStatement("select * from resume where email=?");
                                    ps.setString(1, email);
                                    ResultSet rsr=ps.executeQuery();
                                    if(rsr.next())
                                    {
                                        file_name=rsr.getString("rpath");
                                        session.setAttribute("ses_dw",file_name);

                                          </jsp:scriptlet>

                                &nbsp;&nbsp;&nbsp;&nbsp;<a href="download" class="btn btn-success"> Download Resume </a>

                                <jsp:expression>file_name</jsp:expression>
                                <jsp:scriptlet>
                                    }
                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }
                            </jsp:scriptlet>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <a href="resumebuilder.jsp"> <span class="fa fa-plus"></span> Resume Builder </a>
                                &nbsp;&nbsp;&nbsp;&nbsp;<a href="pdf" class="btn btn-success"> Download Resume Pdf </a>
                            </div>
                        </div>
                    </div>

                </div> <br> <br>


                    <div class="row" style="background-color: #ececec">
                        <div class="col-md-2"> </div>
                        <div class="col-md-8"> <input type="submit" value="Follow" /> </div>
                        <div class="col-md-2"> </div>
                    </div>
                </div>
                <div class="col-md-2"> </div>
            </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
