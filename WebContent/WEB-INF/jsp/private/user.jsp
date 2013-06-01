<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>EMS - Users Management</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/tables_style.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/style_portal.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/cufon-replace.js"></script>
<script type="text/javascript" src="js/Open_Sans_400.font.js"></script>
<script type="text/javascript" src="js/Open_Sans_Light_300.font.js"></script> 
<script type="text/javascript" src="js/Open_Sans_Semibold_600.font.js"></script> 
<script type="text/javascript" src="js/FF-cash.js"></script>  
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<!--[if lt IE 7]>
	<div style=' clear: both; text-align:center; position: relative;'>
		<a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/images/upgrade.jpg" border="0"  alt="" /></a>
	</div>
<![endif]-->
<!--[if lt IE 9]>
	<script type="text/javascript" src="/private/js/html5.js"></script>
	<link rel="stylesheet" href="/private/css/ie.css" type="text/css" media="screen">
<![endif]-->
</head>
<body id="page5">
<div class="bg">
	<div class="main">
	<!-- TOPHEAD --><c:import url="inc/tophead.jsp"/>
	<!-- CONTENT -->
	<c:set var="act">
			<c:url value="/private/userAdd?action=userList" /> 
	</c:set>		
	    <form method="POST" action="${act}" name="frmAddUser">
	        User ID : <input type="text" readonly="readonly" name="id"
	            value="${user.id}" /> - TO BE HIDDEN<br> 
	        First Name : <input
	            type="text" name="fname"
	            value="${user.fname}" /> <br> 
	        Last Name : <input
	            type="text" name="lname"
	            value="${user.lname}" /> <br> 
	        Date of Birth : <input
	            type="text" name="date_of_birth"
	            value="${user.date_of_birth}" /> <br> 	            
	       		<input type="hidden" name="password"
	            value="${user.password}" />
	        Email : <input type="text" name="email"
	            value="${user.email}" /> <br> 
	        Role :	<select name="role">
	        			<option value="admin" 
	        				<c:if test="${user.role == 'admin'}">selected</c:if>
	        				>
	        				admin
	        			</option>
	        			<option value="event_mng" 
	        				<c:if test="${user.role == 'event_mng'}">selected</c:if>
	        				>
	        				event_mng
	        			</option>
	        			<option value="group_mng" 
	        				<c:if test="${user.role == 'group_mng'}">selected</c:if>
	        				>
	        				group_mng
	        			</option>
	        		</select>
	        <br>	        
	        <input type="submit" value="Submit" />         
	        <input type="button" value="Back" onClick="history.go(-1);return true;"/>
        	<c:if test="${param.id eq null }">
        		<input type="reset" value="Reset"/>
        	</c:if>
        	<a class="button-2" href="" id="Submit">Submit</a>
        	<a class="button-2" href="" id="Back">Back</a>
        	<a class="button-2" href="" id="reset">Reset</a>
	    </form>		
	<!-- BOTTOM --><c:import url="inc/bottom.jsp"/>
</html>