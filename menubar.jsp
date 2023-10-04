<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:scriptlet>
    String email = (String) session.getAttribute("session_email");
</jsp:scriptlet>
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
<div class="row menubardiv">
		<div class="col-md-5" id="mynavbar">
			<ul>
				<li> <a href="index.jsp"> Home </a> </li>
				<li> <a href="search-job.jsp"> Search Jobs </a> </li>
				<li> <a href="companies.jsp"> Companies </a> </li>

			</ul>
		</div>
	<div class="col-md-7" id="mynavbar">
		<ul>
			<jsp:scriptlet>
                        if (email == null ? email == null : email.trim().equals("null")) {
                    </jsp:scriptlet>

			<jsp:scriptlet>
                    } else {
                    </jsp:scriptlet>
			<li> <a href="my-applied-jobs.jsp"> My Applied Jobs </a> </li>
			<jsp:scriptlet>
                        }
                    </jsp:scriptlet>
			<li> <a href="about-us.jsp"> About Us </a> </li>
			<li> <a href="contact-us.jsp"> Contact Us </a> </li>
		</ul>
	</div>
	</div>

</body>
</html>