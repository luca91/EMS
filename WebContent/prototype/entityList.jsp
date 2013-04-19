<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Show All Records</title>
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
	            <c:forEach items="${records}" var="record">
	                <tr>
	                    <td><c:out value="${record.id}" /></td>
	                    <td><c:out value="${record.fname}"/></td>
	                    <td><c:out value="${record.lname}" /></td>
	                    <td><c:out value="${record.email}" /></td>
	                    <td><a href="PrototypeController?action=edit&id=${record.id}">Update</a></td>
	                    <td><a href="PrototypeController?action=delete&id=${record.id}">Delete</a></td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    <p><a href="PrototypeController?action=insert">Add s record</a></p>
	</body>
</html>