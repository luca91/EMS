<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<link rel="stylesheet" href="css/forms_public.css" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Enrollment result</title>
</head>
<body>
	<div id="stylized" class="myform">
	<h3>Confirmation of enrollment</h3>


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