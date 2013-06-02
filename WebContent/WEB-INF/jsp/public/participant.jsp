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
<title>Enrollment form</title>
</head>
<body>
	<hr />
	<c:set var="act">
		<c:url value="/public/participantAdd" />
	</c:set>
	
	<div id="stylized" class="myform">
		<form method="POST" action="${act}" name="frmAddParticipan" id="form">
			<h3>Registration to event</h3>
			<p>Welcome to the registration as participant for this event</p>
			<label>Group ID : 
			<span class="small">ID of your group</span>
			</label>
			<input type="text" readonly="readonly" name="id_group" value="${id_group}" /><br><br><br>
			<label>First Name :
			<span class="small">Your first name</span>
			</label>
			<input type="text" name="fname" value="${record.fname}" /><br><br><br>
			<label>Last  Name :
			<span class="small">Your last name</span>
			</label>
			<input type="text" name="lname" value="${record.lname}" /><br><br><br>
			<label>Email : 
			<span class="small">ID of your group</span>
			</label>
			<input type="text" name="email" readonly="readonly" value="${email}" /><br><br><br>
			<label>Birth date : 
			<span class="small">ID of your group</span>
			</label>
			<input type="text" name="date_of_birth" value="${record.date_of_birth}" /><br><br><br>
			<p>
			<!-- BUTTONS -->			
		    <input type="submit" value="Submit" class="input" />
		    <input type="button" value="Back" onClick="history.go(-1);return true;" class="input" />
	        <c:if test="${param.id eq null }">
        		<input type="reset" value="Reset" class="input" />
       		</c:if>
			</p>
			
			<!-- 
			Group ID : <input type="text" readonly="readonly" name="id_group" value="${id_group}" /><br> 
			First Name : <input type="text" name="fname" value="${record.fname}" /> <br /> 
			Last Name : <input type="text" name="lname" value="${record.lname}" /> <br /> 
			Email: <input type="text" name="email" readonly="readonly" value="${email}" /> <br />
			Date of birth : <input type="text" name="date_of_birth" value="${record.date_of_birth}" /> <br /> 
			
			<input type="submit" value="Submit" />
			-->
		</form>
	</div>
</body>
</html>