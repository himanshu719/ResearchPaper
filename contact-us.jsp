
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.dbcon.DbCon"%>


<!DOCTYPE html>

<jsp:include page="CheckCookie"></jsp:include>

<jsp:scriptlet>
    String email = (String) session.getAttribute("session_email");
</jsp:scriptlet>

<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Job Portal Website</title>

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


  <link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<div class="container-fluid">

  <jsp:scriptlet>
                if (email == null ? email == null : email.trim().equals("null")) {
            </jsp:scriptlet>
  <jsp:include page="header.jsp"></jsp:include>
  <jsp:scriptlet>
            } else {
            </jsp:scriptlet>
  <jsp:include page="profileheader.jsp"></jsp:include>
  <jsp:scriptlet>
                }
            </jsp:scriptlet>
  <jsp:include page="menubar.jsp"></jsp:include>

  <div class="row">
    <div class="col-md-3">
      <ul>
        <li> <a href=""> Falcon </a> </li>
        <li> <a href=""> Infosys </a> </li>
        <li> <a href=""> Wipro </a> </li>
        <li> <a href=""> IBM </a> </li>
        <li> <a href=""> Google </a> </li>
      </ul>
    </div>
    <div class="col-md-6" style="background-color: #f9f8f9; margin-top: 10px">
      <jsp:scriptlet>
                    if(email == null ? email == null : email.trim().equals("null"))
                    {
                    </jsp:scriptlet>
      <jsp:include page="contact-nuser.jsp"></jsp:include>
      <jsp:scriptlet>
                    }
                    else
                    {
                    </jsp:scriptlet>
      <jsp:include page="contact-us-user.jsp"></jsp:include>
      <jsp:scriptlet>
                    }
                    </jsp:scriptlet>
    </div>
    <div class="col-md-3">
      <ul>
        <li> <a href=""> Falcon </a> </li>
        <li> <a href=""> Infosys </a> </li>
        <li> <a href=""> Wipro </a> </li>
        <li> <a href=""> IBM </a> </li>
        <li> <a href=""> Google </a> </li>
      </ul>
    </div>
  </div>

  <div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-8">

    </div>
    <div class="col-md-2"></div>
  </div>

  <jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>