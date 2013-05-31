<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
	<title>Participant form</title>
	</head>
	<body>
		<c:import url="inc/header.jsp"/>
		
		<c:set var="act">
			<c:url value="/private/participantAdd?action=edit" /> 
		</c:set>

	    <form method="POST" action="${act}" name="frmAddParticipan">
	        Participant ID : <input type="text" readonly="readonly" name="id"
	            value="${record.id}" /> - TO BE HIDDEN<br /> 
	        Group ID : <input
	            type="text" readonly="readonly" name="id_group" 
	            value="${id_group}" /> - TO BE HIDDEN <br /> 
	        First Name : <input
	            type="text" name="fname"
	            value="${record.fname}" /> <br /> 
	        Last Name : <input
	            type="text" name="lname"
	            value="${record.lname}" /> <br /> 
	        Email : <input
	            type="text" name="email"
	            value="${record.email}" /> <br /> 	            	            
	        Date of birth : <input type="text" name="date_of_birth"
	            value="${record.date_of_birth}" /> <br />     
	        <input type="hidden" name="registration_date"
	            value="${record.registration_date}" /> <br /> 
	        Approved : 
	        	<select name="approved">
	        		<option value="false" <c:if test="${record.approved == 'false'}">selected </c:if> >false</option>
	        	    <option value="true" <c:if test="${record.approved == 'true'}">selected</c:if> >true</option>
	        	</select> 
				<br /> 
	       <!--  blocked :  --><input type="hidden" name="blocked"
	            value="${record.blocked}" /> <br /> 	            	          	                  
	        <input type="submit" value="Submit" />
	        <input type="button" value="Back" onClick="history.go(-1);return true;"/>
        	<c:if test="${param.id eq null }">
        		<input type="reset" value="Reset"/>
        	</c:if>	        
	    </form>
	</body>
</html>