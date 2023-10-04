<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <%
String name=(String)session.getAttribute("session_name");
String file=(String)session.getAttribute("ses_img");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>

        <link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<div class="row header_bg">
		<div class="col-md-4">
			<img src="images/falcon.png" height="50" />
			<span class="logo_text_design"> Job Portal </span>
		</div>
		
		<div class="col-md-6">
		<span  style="color: red"><a href="Profile.jsp"  style="color: white;font-size:15px;text-decoration:none"><img src="images/<jsp:expression>file</jsp:expression>" height="70px" style="border-radius:50%">&nbsp&nbsp<%=name %> </a> </span>
		</div>
		
		<div class="col-md-2">
		 <span style="color: yellow"> / </span> <a href="Logout" class="hyperlinks_design"> Logout </a>
		</div>
	</div>

</body>
</html>