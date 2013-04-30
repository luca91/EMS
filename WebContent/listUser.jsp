<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Show All Users</title>
	</head>
	<body>
	    <table border=1>
	        <thead>
	            <tr>
	                <th>User Id</th>
	                <th>First Name</th>
	                <th>Last Name</th>
	                <th>Password</th>
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
	                    <td><a href="UserController?action=edit&id=${user.id}">Update</a></td>
	                    <td><a href="UserController?action=delete&id=${user.id}">Delete</a></td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    <p><a href="UserController?action=insert">Add User</a></p>
	    <a href="home.jsp"> &lt;&lt; </a>
	</body>
</html>