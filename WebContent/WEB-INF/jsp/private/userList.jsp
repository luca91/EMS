<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Show All Users</title>
	</head>
<script>
function confirmDelete(){
	var msg ="Are you sure to delete the record?";
	if(confirm(msg)){
		return true;
	}
	else {
		return false;
	}
}
</script>	
	
	<body>
		<c:import url="inc/header.jsp"/>
		<h1>Users</h1>	
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
	                    <td><a href="<c:url value='/private/userDelete?action=delete&id=${user.id}'/>" onclick="return confirmDelete();">Delete</a></td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    <p><a href="user.jsp?action=insert">Add User</a></p>
	</body>
</html>