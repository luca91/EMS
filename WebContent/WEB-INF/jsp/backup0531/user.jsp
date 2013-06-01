<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css"
	href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Add new user</title>
</head>
<body>
	<c:import url="inc/header.jsp" />

	<c:set var="act">
		<c:url value="/private/userAdd?action=userList" />
	</c:set>

	<form method="POST" action="${act}" name="frmAddUser">
		User ID : <input type="text" readonly="readonly" name="id"
			value="${user.id}" /> - TO BE HIDDEN<br /> First Name : <input
			type="text" name="fname" value="${user.fname}" /> <br /> Last Name
		: <input type="text" name="lname" value="${user.lname}" /> <br />
		Date of Birth : <input type="text" name="date_of_birth"
			value="${user.date_of_birth}" /> <br /> <input type="hidden"
			name="password" value="${user.password}" /> Email : <input
			type="text" name="email" value="${user.email}" /> <br /> Role : <select
			name="role">
			<option value="admin"
				<c:if test="${user.role == 'admin'}">selected</c:if>>
				admin</option>
			<option value="event_mng"
				<c:if test="${user.role == 'event_mng'}">selected</c:if>>
				event_mng</option>
			<option value="group_mng"
				<c:if test="${user.role == 'group_mng'}">selected</c:if>>
				group_mng</option>
		</select> <br /> <input type="submit" value="Submit" /> <input type="button"
			value="Back" onClick="history.go(-1);return true;" />
		<c:if test="${param.id eq null }">
			<input type="reset" value="Reset" />
		</c:if>

	</form>
</body>
</html>