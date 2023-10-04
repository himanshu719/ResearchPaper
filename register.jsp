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
 <div class="container-fluid">
            <jsp:include page="header.jsp"></jsp:include>
            <jsp:include page="menubar.jsp"></jsp:include>
 
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="reg_div_design">
                            <form method="POST" name="regform" action="Reg">
                                <br> <h2> Register Here </h2> <br>
                                <input type="text" placeholder="Enter Name" name="name1" class="reglog_tf_design"  /> <br> <span id="name_error" style="color: red"> </span> <br> <br>
                                <input type="text" placeholder="Enter Email" name="email1" class="reglog_tf_design"  /> <br> <span id="email_error" style="color: red"> </span> <br> <br>
                                <input type="password" placeholder="Enter Password" name="pass1" class="reglog_tf_design" /> <br> <span id="password_error" style="color: red"> </span> <br> <br>
                                <b> Select Gender : </b> <input type="radio" name="gender1" value="Male" /> Male <input type="radio" name="gender1" value="Female" /> Female <br> <span id="gender_error" style="color: red"> </span> <br> <br>
                                <b> Select Fields : </b> <input type="checkbox" name="field1" value="Information Technology" /> Information Technology <br>
                                <input type="checkbox" name="field1" value="Marketing" /> Marketing
                                <input type="checkbox" name="field1" value="Finance" /> Finance <br> <br>
                                <select name="city1" class="reglog_tf_design">
                                    <option> Select City </option>
                                    <option value="Jaipur"> Jaipur </option>
                                    <option value="Delhi"> Delhi </option>
                                    <option value="Kota"> Kota </option>
                                      <option value="Udaipur"> Udaipur </option>
                                </select> <br> <span id="city_error" style="color: red"> </span> <br> <br>
                                <input type="submit" value="Register" class="btn btn-primary" /> <br> <br>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-3"></div>
                </div>

            <jsp:include page="footer.jsp"></jsp:include>
        </div>


</body>
</html>