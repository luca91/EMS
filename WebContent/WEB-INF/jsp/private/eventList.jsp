<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Show All Events</title>
	</head>
	<body>
		<table>
			<tr>
				<td>email:</td>
				<td>${systemUser.email}</td>
			</tr>
			<tr>
				<td>role:</td>
				<td>${systemUser.role}</td>
			</tr>
			<tr>
				<td>date:</td>
				<td>
					<jsp:useBean id="today" class="java.util.Date" scope="page" />
					<fmt:formatDate value="${today}" pattern="dd MMM yyyy - HH:mm" />
				</td>
			</tr>
		</table>
		
		<hr/>
		<a href='<c:url value="/private/index.html"/>'>Home Page</a> |
		<a href='<c:url value="/private/userList.html"/>'>Users Management</a> |
		<a href='<c:url value="/private/eventList.html"/>'>Events Management</a> |
		<a href='<c:url value="/private/groupList.html"/>'>Groups Management</a> |
		<a href='<c:url value="/private/participantList.html"/>'>Participants Management</a>
		<hr/>
		
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
	                    <td><a href="<c:url value='/private/eventDelete?action=delete&id=${record.id}'/>">Delete</a></td>
	                	<td><a href="<c:url value='/private/groupList.html?action=listRecord&id_event=${record.id}'/>">Groups</a></td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    <p><a href="event.jsp?action=insert">Add Event</a></p>
	</body>
</html>