<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="reg_div_design">
                    <form method="POST" action="log">
                        <br> <h2> Login Here </h2> <br>
                        <input type="text" placeholder="Enter Email" name="email1" class="reglog_tf_design" /> <br> <br>
                        <input type="password" placeholder="Enter Password" name="pass1" class="reglog_tf_design" /> <br> <br>
                        <input type="checkbox" name="rememberme1" value="remember-me" /> Remember Me <br> <br>
                        <input type="submit" value="Login" class="btn btn-primary" /> <br> <br> <br>
                    </form>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>

</body>
</html>