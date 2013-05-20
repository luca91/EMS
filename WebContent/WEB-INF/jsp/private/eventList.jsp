<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Show All Events</title>
	</head>
<script>
function confirmDelete(){
	var msg ="Are you sure to delete the record?";
	if(confirm(msg)){
		return true;
	}
	else {
		return false;
	}
}
</script>	
	<body>
		<c:import url="inc/header.jsp"/>
		<h1>Events</h1>	
	    <table border=1>
	        <thead>
	            <tr>
	                <th>Event Id</th>
	                <th>Manager Id</th>
	                <th>Event Name</th>
	                <th>Description</th>
	                <th>Start</th>
	                <th>End</th>
	                <th>Enrollment_start</th>
	                <th>Enrollment_end</th>
	                <th colspan=3>Action</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach items="${records}" var="record">
	                <tr>
	                    <td>${record.id}</td>
	                    <td>${record.id_manager}</td>
	                    <td>${record.name}</td>
	                    <td>${record.description}</td>
	                    <td>${record.start}</td>
	                    <td>${record.end}</td>
	                    <td>${record.enrollment_start}</td>
	                    <td>${record.enrollment_end}</td>	                    	                    	                    	                    
	                    <td><a href="<c:url value='/private/event.jsp?action=edit&id=${record.id}'/>">Update</a></td>
	                    <td><a href="<c:url value='/private/eventDelete?action=delete&id=${record.id}'/>" onclick="return confirmDelete();">Delete</a></td>
	                	<td><a href="<c:url value='/private/groupList.html?action=listRecord&id_event=${record.id}'/>">Groups</a></td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    <p><a href="event.jsp?action=insert">Add Event</a></p>
	</body>
</html>