<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link type="text/css"
	    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
	<title>Add new user</title>
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
		
		<c:set var="act">
			<c:url value="/private/userAdd?action=userList" /> 
		</c:set>
		
	    <form method="POST" action="${act}" name="frmAddUser">
	        User ID : <input type="text" readonly="readonly" name="id"
	            value="${user.id}" /> <br /> 
	        First Name : <input
	            type="text" name="fname"
	            value="${user.fname}" /> <br /> 
	        Last Name : <input
	            type="text" name="lname"
	            value="${user.lname}" /> <br /> 
	        Date of Birth : <input
	            type="text" name="date_of_birth"
	            value="${user.date_of_birth}" /> <br /> 	            
	        Password : <input type="text" name="password"
	            value="${user.password}" /> <br />
	        Email : <input type="text" name="email"
	            value="${user.email}" /> <br /> 
	        Role : <input type="text" name="role"
	            value="${user.role}" /> <br /> 	            
	        <input type="submit" value="Submit" />
	    </form>
	</body>
</html>