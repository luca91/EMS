<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Show All Users</title>
	</head>
	<body>
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
		<a href='<c:url value="/private/gorupList.html"/>'>Groups Management</a> |
		<a href='<c:url value="/private/participantList.html"/>'>Participants Management</a>
		<hr/>
		
	    <table border=1>
	        <thead>
	            <tr>
	                <th>User Id</th>
	                <th>First Name</th>
	                <th>Last Name</th>
	                <th>Email</th>
	                <th colspan=2>Action</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach items="${users}" var="user">
	                <tr>
	                    <td>${user.id}</td>
	                    <td>${user.fname}</td>
	                    <td>${user.lname}</td>
	                    <td>${user.email}</td>
	                    <td><a href="<c:url value='/private/user.jsp?action=edit&id=${user.id}'/>">Update</a></td>
	                    <td><a href="<c:url value='/private/userDelete?action=delete&id=${user.id}'/>">Delete</a></td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    <p><a href="user.jsp?action=insert">Add User</a></p>
	</body>
</html>