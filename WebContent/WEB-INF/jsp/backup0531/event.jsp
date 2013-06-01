<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<!-- CONTROL ACT -->
<script type="text/javascript">
$(document).ready(function(){
	$("#act-control").append("<h3>Event Management</h3>");
	/*
	if('${act}' == 'insert'){
  	$("#act-control").append("<h3>Add Event</h3>");}
	else('${act}' == 'edit'){
		  	$("#act-control").append("<h3>Edit Event</h3>");*/
  	$("#submitajax").click(function(){
  		var initialPage = window.location.pathname;
  	    window.location.replace(initialPage+'#events');
	  	$("#ajax-parse").load("eventList.html");
	  });	
});
</script>
<script type="text/javascript">
function reloadPageWithHash() {
	  var initialPage = window.location.pathname;
	  window.location.replace(initialPage+'#events');
	  $("#ajax-parse").empty();
	  $("#ajax-parse").load("eventList.html");
	} 
</script>
<title>Event Form</title>
</head>
<body>
<div id="act-control"></div>

	<c:set var="act">
		<c:url value="/private/eventAdd?action=eventList" />
	</c:set>

	<form method="POST" action="${act}" name="frmAddEvent">
		Event ID : <input type="text" readonly="readonly" name="id"
			value="${record.id}" /> - TO BE HIDDEN<br /> manager ID :
		<c:choose>
			<c:when
				test="${record.id_manager != null  && sessionScope.systemUser.role == 'admin'}">
				<select name="id_manager">
					<c:forEach items="${listOfEvent_mng}" var="options">
						<option value="${options.id }"
							<c:if test="${options.id == record.id_manager }">selected</c:if>>${options.id
							} - ${options.fname} ${options.lname }</option>
					</c:forEach>
				</select>
			</c:when>
			<c:when
				test="${record.id_manager != null && sessionScope.systemUser.role == 'event_mng'}">
				<select name="id_manager" >
					<c:forEach items="${listOfEvent_mng}" var="options">
						<option value="${options.id }"
							<c:if test="${options.id == record.id_manager }">selected</c:if>>${options.id
							} - ${options.fname} ${options.lname }</option>
					</c:forEach>
				</select>
			</c:when>
			<c:when test="${sessionScope.systemUser.role == 'event_mng' }">
				<input type="text" name="id_manager"
					value="${sessionScope.systemUser.id}" /> - TO BE HIDDEN!
	            		
	            	</c:when>
			<c:otherwise>
				<select name="id_manager">
					<c:forEach items="${listOfEvent_mng}" var="record">
						<option value="${record.id }">${record.id } -
							${record.fname} ${record.lname }</option>
					</c:forEach>
				</select>
				<input type="text" name="id_manager" value="" />
			</c:otherwise>
		</c:choose>
		<br /> Event Name : <input type="text" name="name"
			value="${record.name}" /> <br /> Description : <input type="text"
			name="description" value="${record.description}" /> <br /> Start :
		<input type="text" name="start" value="${record.start}" /> <br />
		End : <input type="text" name="end" value="${record.end}" /> <br />
		Enrollment start : <input type="text" name="enrollment_start"
			value="${record.enrollment_start}" /> <br /> Enrollment end : <input
			type="text" name="enrollment_end" value="${record.enrollment_end}" />
		<br />
		<a class="button-2" id="submitajax">Submit</a> 
		<input class="button-2" type="submit" value="Submit" id="submitajax"/>
		<a class="button-2" onClick=reloadPageWithHash() >Back</a>
		<c:if test="${param.id eq null }">
			<input type="reset" value="Reset" />
		</c:if>
	</form>		
</body>
</html>