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
<title>Enrollment form</title>
</head>
<body>
	<hr />
	<c:set var="act">
		<c:url value="/public/participantAdd" />
	</c:set>

	<form method="POST" action="${act}" name="frmAddParticipan">
		Group ID : <input type="text" readonly="readonly" name="id_group"
			value="${id_group}" /> <br /> First Name : <input type="text"
			name="fname" value="${record.fname}" /> <br /> Last Name : <input
			type="text" name="lname" value="${record.lname}" /> <br /> Email: <input
			type="text" name="email" readonly="readonly" value="${email}" /> <br />
		Date of birth : <input type="text" name="date_of_birth"
			value="${record.date_of_birth}" /> <br /> <input type="submit"
			value="Submit" />
	</form>
</body>
</html>