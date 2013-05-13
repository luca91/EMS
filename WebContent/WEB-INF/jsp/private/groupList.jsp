<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Show All Groups</title>
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
		<a href='<c:url value="/private/gorupList.html"/>'>Groups Management</a> |
		<a href='<c:url value="/private/participantList.html"/>'>Participants Management</a>
		<hr/>
		
	    <table border=1>
	        <thead>
	            <tr>
	                <th>Group Id</th>
	                <th>Event Id</th>
	                <th>Group referent Id</th>
	                <th>Max Group Number</th>
	                <th>Blocked</th>
	                <th colspan=2>Action</th>
	            </tr>
	        </thead>
	              
	        <tbody>
	            <c:forEach items="${records}" var="record">
	                <tr>
	                    <td>${record.id}</td>
	                    <td>${record.id_event}</td>
	                    <td>${record.id_group_referent}</td>
	                    <td>${record.max_group_number}</td>
	                    <td>${record.blocked}</td>                    	                    	                    	                    
	                    <td><a href="<c:url value='/private/group.jsp?action=edit&id=${record.id}'/>">Update</a></td>
	                    <td><a href="<c:url value='/private/groupDelete?action=delete&id=${record.id}'/>">Delete</a></td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    <p><a href="group.jsp?action=insert">Add Group</a></p>
	</body>
</html>