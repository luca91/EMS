<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
		<table>
			<tr>
				<td>email:</td>
				<td>${systemUser.email} - ${systemUser.id}</td>
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
		<c:choose>
			<c:when test="${systemUser.role == 'admin' }">
				<% System.out.println("admin"); %>
				<a href='<c:url value="/private/index.html"/>'>Home Page</a> |
				<a href='<c:url value="/private/userList.html"/>'>Users Management</a> |
				<a href='<c:url value="/private/eventList.html"/>'>Events Management</a> |
				<a href='<c:url value="/private/groupList.html"/>'>Groups Management</a> |
				<a href='<c:url value="/private/participantList.html"/>'>Participants Management</a>
			</c:when>
			<c:when test="${systemUser.role == 'event_mng' }">
				<% System.out.println("event_mng"); %>
				<a href='<c:url value="/private/index.html"/>'>Home Page</a> |
				<a href='<c:url value="/private/eventList.html"/>'>Events Management</a> |
				<a href='<c:url value="/private/groupList.html"/>'>Groups Management</a> |
				<a href='<c:url value="/private/participantList.html"/>'>Participants Management</a>
			</c:when>
			<c:when test="${systemUser.role == 'group_mng' }">
				<% System.out.println("group_mng"); %>
				<a href='<c:url value="/private/index.html"/>'>Home Page</a> |
				<a href='<c:url value="/private/groupList.html"/>'>Groups Management</a> |
				<a href='<c:url value="/private/participantList.html"/>'>Participants Management</a>
			</c:when>
		</c:choose>
				
		<hr/>
		