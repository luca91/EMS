<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EMS - Enrollment Management System - Change Password Page</title>
</head>
<body>
	<h1>Enrollment Management System</h1>
	<h1>Reset Password</h1>
	<c:choose>
		<c:when test="${param.email == null }">

			<c:set var="act">
				<c:url value="/public/changePasswordSend" />
			</c:set>
			<form action="${act}" method="post" name="frmChangePasswordSend">
				email: <input type="text" name="email" /> <br /> <input
					type="submit" value="Submit" />
			</form>
		</c:when>
		<c:when test="${message != 2 }">
			<c:set var="act">
				<c:url value="/public/changePasswordDo" />
			</c:set>
			<form action="${act}" method="post" name="frmChangePasswordDo">
				email: <input type="text" name="email" readonly
					value="${param.email}" />
				<c:if test="${message == 6 }"> Email not recognized</c:if>
				<br /> Old password : <input type="password" name="oldPassword"
					value="${oldPassword }" />
				<c:if test="${message == 5 }"> Incorrect password</c:if>
				<br /> New password : <input type="password" name="newPassword"
					value="${newPassword }">
				<c:if test="${message == 4 }"> Empty password</c:if>
				<br /> Confirm new password : <input type="password"
					name="confirmedPassword" value="${confirmedPassword }">
				<c:if test="${message == 3 }"> Confirmed password wrong</c:if>
				<br /> <input type="hidden" name="action" value="change" readonly><br />
				<input type="submit" value="Submit" />
			</form>
		</c:when>
		<c:otherwise>
			<c:if test="${message == 2 }">
				<br /> Password changed
		        	<c:url value="/private/index.html" var="url" />
				<br />
				<br />
				<a href="${url }">Link</a> to enter private site
		        </c:if>
		</c:otherwise>
	</c:choose>
</body>
</html>