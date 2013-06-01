<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>EMS - Events Management</title>
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
	<!-- TOPHEAD -->
	<c:import url="inc/tophead.jsp"/>
	<!-- CONTENT -->
	<c:set var="act">
		<c:url value="/private/eventAdd?action=eventList" /> 
	</c:set>	
	    <form method="POST" action="${act}" name="frmAddEvent">
	        Event ID : <input type="text" readonly="readonly" name="id"
	            value="${record.id}" /> - TO BE HIDDEN<br> 
	        manager ID : 
	            <c:choose>
	               	<c:when test="${record.id_manager != null  && sessionScope.systemUser.role == 'admin'}">
	            		<select name="id_manager">
	           				<c:forEach items="${listOfEvent_mng}" var="options">	               
	                			<option value="${options.id }" 
	                				<c:if test="${options.id == record.id_manager }">selected</c:if> 
	                			>${options.id } - ${options.fname} ${options.lname }</option>
	            			</c:forEach>
	            		</select>
	            	</c:when>
	               	<c:when test="${record.id_manager != null && sessionScope.systemUser.role == 'event_mng'}">
	            		<select name="id_manager" disabled>
	           				<c:forEach items="${listOfEvent_mng}" var="options">	               
	                			<option value="${options.id }"
	                				<c:if test="${options.id == record.id_manager }">selected</c:if> 
	                			>${options.id } - ${options.fname} ${options.lname }</option>
	            			</c:forEach>
	            		</select>
	            	</c:when>	            	
	            	<c:when test="${sessionScope.systemUser.role == 'event_mng' }">
	            		<input
	            			type="text" name="id_manager"
	            			value="${sessionScope.systemUser.id}"
	            			readonly="readonly"
	            		/> - TO BE HIDDEN!
	            		
	            	</c:when>
	            	<c:otherwise>
	               		<select name="id_manager">
	           				<c:forEach items="${listOfEvent_mng}" var="record">	               
	                			<option value="${record.id }">${record.id } - ${record.fname} ${record.lname }</option>
	            			</c:forEach>
	            		</select>
	            		<input
	            			type="text" name="id_manager"
	            			value=""
	            		/>
	            	</c:otherwise>
	            </c:choose>
	             <br> 
	        Event Name : <input
	            type="text" name="name"
	            value="${record.name}" /> <br> 
	        Description : <input
	            type="text" name="description"
	            value="${record.description}" /> <br> 	            
	        Start : <input type="text" name="start"
	            value="${record.start}" /> <br>
	        End : <input type="text" name="end"
	            value="${record.end}" /> <br> 
	        Enrollment start : <input type="text" name="enrollment_start"
	            value="${record.enrollment_start}" /> <br> 
	      	Enrollment end : <input type="text" name="enrollment_end"
	            value="${record.enrollment_end}" /> <br>
	        <!-- BUTTONS -->             
	        <input type="submit" value="Submit" />       
	        <input type="button" value="Back" onClick="history.go(-1);return true;"/>
        	<c:if test="${param.id eq null }">
        		<input type="reset" value="Reset"/>
        	</c:if>
        	<a class="button-2" href="" id="Submit">Submit</a>
        	<a class="button-2" href="" id="Back">Back</a>
        	<a class="button-2" href="" id="reset">Reset</a>
	    </form>		
	<!-- BOTTOM -->
	<c:import url="inc/bottom.jsp"/>	
</html>