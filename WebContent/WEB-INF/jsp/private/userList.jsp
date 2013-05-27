<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<hr>
<h1>User Id</h1>
<h1>First Name</h1>
<h1>Last Name</h1>
<h1>Email</h1>
<h1>Action</h1>
<c:forEach items="${users}" var="user">
	<h3>
		<% System.out.println("admin"); %>
		<% System.out.println("${systemUser.role}"); %>
		<% out.print("${user.id}"); %>
		out.print(${user.fname}); out.print(${user.lname});
		out.print(${user.email});
	</h3>
</c:forEach>
<p>
	<a href="user.jsp?action=insert">Add User</a>
</p>