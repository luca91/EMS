<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
System.out.println("eventListTest.html");
%>
<h3 class="p0">${record.id}</h3>
<br>
<h3 class="p0">LOL</h3>
<br>
<c:choose>
	<c:when test="${systemUser.role == 'admin' }">
		<% System.out.println("admin"); %>
		<li><a href='<c:url value="/private/index.html"/>'>Home Page</a></li>
		<li><a href='<c:url value="/private/userList.html"/>'>Users
				Management</a></li>
		<li><a href='<c:url value="/private/eventList.html"/>'>Events
				Management</a></li>
		<li><a href='<c:url value="/private/groupList.html"/>'>Groups
				Management</a></li>
		<li><a href='<c:url value="/private/participantList.html"/>'>Participants
				Management</a></li>
	</c:when>
	<c:when test="${systemUser.role == 'event_mng' }">
		<% System.out.println("event_mng"); %>
		<li><a href='<c:url value="/private/index.html"/>'>Home Page</a></li>
		<li><a href='<c:url value="/private/eventList.html"/>'>Events
				Management</a></li>
		<li><a href='<c:url value="/private/groupList.html"/>'>Groups
				Management</a></li>
		<li><a href='<c:url value="/private/participantList.html"/>'>Participants
				Management</a></li>
	</c:when>
	<c:when test="${systemUser.role == 'group_mng' }">
		<% System.out.println("group_mng"); %>
		<li><a href='<c:url value="/private/index.html"/>'>Home Page</a></li>
		<li><a href='<c:url value="/private/groupList.html"/>'>Groups
				Management</a></li>
		<li><a href='<c:url value="/private/participantList.html"/>'>Participants
				Management</a></li>
	</c:when>
</c:choose>
<h3>EVENTS</h3>
<br>
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
				<td><a
					href="<c:url value='/private/event.jsp?action=edit&id=${record.id}'/>">Update</a></td>
				<td><a
					href="<c:url value='/private/eventDelete?action=delete&id=${record.id}'/>"
					onclick="return confirmDelete();">Delete</a></td>
				<td><a
					href="<c:url value='/private/groupList.html?action=listRecord&id_event=${record.id}'/>">Groups</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<p>
	<a href="event.jsp?action=insert">Add Event</a>
</p>