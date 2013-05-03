<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EMS - Enrollment Management System - Homepage</title>
</head>
<body>
<h1>Welcome to Enrollment Management System</h1>

<%
String name = request.getUserPrincipal().getName();
out.println(name);

%>
${pageContext.request.userPrincipal.name}

${pageContext.request.remoteUser}
<table border="1">
<tr>
	<td><a href="index.jsp">Home</a></td>
	<td><a href="#">Events</a></td>
	<td><a href="#">Media</a></td>
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