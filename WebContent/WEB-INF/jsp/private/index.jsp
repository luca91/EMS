<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>EMS - Enrollment Management System - Homepage</title>
	</head>
	<body>
		<%
		/* String name = request.getUserPrincipal().getName();
		String role = "";
		
		if (request.isUserInRole("admin")) {
		   role = "admin";
		} else if (request.isUserInRole("staff")) {
			role = "staff";
		} else if (request.isUserInRole("student")) {
			role = "student";
		}
		out.println("User: " + name);
		out.println("<br/>");
		out.println("Role: " + role);
		out.println("<br/>"); */
		%>
		<%-- ${pageContext.request.userPrincipal.name} 
		${pageContext.request.remoteUser} --%>
		<table>
			<tr>
				<td>email:</td>
				<td>${systemUser.email}</td>
			</tr>
			<tr>
				<td>role:</td>
				<td>${systemUser.role}</td>
			</tr>
			<tr>
				<td>date:</td>
				<td>
					<jsp:useBean id="today" class="java.util.Date" scope="page" />
					<fmt:formatDate value="${today}" pattern="dd MMM yyyy - HH:mm" />
				</td>
			</tr>
		</table>

		<hr/>
		<a href='<c:url value="/private/index.html"/>'>Home Page</a> |
		<a href='<c:url value="/private/userList.html"/>'>Users Management</a> |
		<a href='<c:url value="/private/eventList.html"/>'>Events Management</a> |
		<a href='<c:url value="/private/groupList.html"/>'>Groups Management</a> |
		<a href='<c:url value="/private/participantList.html"/>'>Participants Management</a>
		<hr/>
		
		<h1>Enrollment Management System - private site</h1>
		<h2>Home Page</h2>
	</body>
</html>