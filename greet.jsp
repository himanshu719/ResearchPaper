<!DOCTYPE html>
<html>
<head>
    <title>Greeting Page</title>
</head>
<body>
<h1>Greeting Page</h1>
<form action="greet" method="post">
    Enter your name: <input type="text" name="name" />
    <input type="submit" value="Submit" />
</form>
<p>
    <%
        String greeting = (String) request.getAttribute("greeting");
        if (greeting != null) {
            out.println(greeting);
        }
    %>
</p>
</body>
</html>
