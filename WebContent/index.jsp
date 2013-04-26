<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EMS - Enrollment Management System - Homepage</title>
</head>
<body>
<h1>Welcome to Enrollment Management System</h1>
<jsp:forward page="/UserController?action=listUser" />
<table border="1">
<tr>
	<td>Home</td>
	<td>Events</td>
	<td>Media</td>
	<td><a href="login.jsp">Login</a></td>
</tr>
</table>
<br>
<table border="1">
<tr>
<td>MAIN FLOW OF INFO<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br></td>
</tr>
</table>
<table>
<tr>
<td>footer</td>
</tr>
</table>
</body>
</html>