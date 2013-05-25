<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:choose>
	<c:when test="${systemUser.role == 'admin' }">
		<% System.out.println("admin"); %>
		<li><a href='<c:url value="/private/index.html"/>'>Home Page</a></li>
		<li><a href='<c:url value="/private/userList.html"/>'>Users Management</a></li>
		<li><a href='<c:url value="/private/eventList.html"/>'>Events Management</a></li>
		<li><a href='<c:url value="/private/groupList.html"/>'>Groups Management</a></li>
		<li><a href='<c:url value="/private/participantList.html"/>'>Participants Management</a></li>
	</c:when>
	<c:when test="${systemUser.role == 'event_mng' }">
		<% System.out.println("event_mng"); %>
		<li><a href='<c:url value="/private/index.html"/>'>Home Page</a></li>
		<li><a href='<c:url value="/private/eventList.html"/>'>Events Management</a></li>
		<li><a href='<c:url value="/private/groupList.html"/>'>Groups Management</a></li>
		<li><a href='<c:url value="/private/participantList.html"/>'>Participants Management</a></li>
	</c:when>
	<c:when test="${systemUser.role == 'group_mng' }">
		<% System.out.println("group_mng"); %>
		<li><a href='<c:url value="/private/index.html"/>'>Home Page</a></li>
		<li><a href='<c:url value="/private/groupList.html"/>'>Groups Management</a></li>
		<li><a href='<c:url value="/private/participantList.html"/>'>Participants Management</a></li>
	</c:when>
</c:choose>
		