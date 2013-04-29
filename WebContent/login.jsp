<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EMS - Enrollment Management System - Login</title>
</head>
<body>
<br>
<form name="loginForm" action="LoginController" method="POST"><!-- TO DO: handle encryption -->
E-mail:<input type="text" name="username"><br>
Password:<input type="password" name="password"><br>
<input type="submit" value="Login"><!-- the registration opportunity should be done as a response for the --><!-- <input type="submit" value="Register"><br>  -->
</form>
</body>
</html>