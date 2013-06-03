<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/forms_public.css" type="text/css" media="screen">
<title>Confirmation of enrollment</title>
</head>
<body>
	<div id="stylized" class="myform">
	<h1>Confirmation of enrollment</h1>


	<c:if test="${param.sentEmail == 'y'}">
		<p>Your enrollment has been registered. An email has been sent to
			your address.</p>
	
		<p>
			Click <a href="<c:url value='/public/index.html'/>">here</a> to go to
			the home page of the Enrollment Management System.
		</p>
	</c:if>

	<c:if test="${param.sentEmail != 'y'}">
		<p>We are afraid but there are not anymore available place!</p>
		<p>Contact a Group Referent to get a new Invitation when more places will be available</p>
	
		<p>
			Click <a href="<c:url value='/public/index.html'/>">here</a> to go to
			the home page of the Enrollment Management System.
		</p>
	</c:if>	
	</div>
</body>
</html>