<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Show Groups</title>
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
	<c:import url="inc/header.jsp" />
	<c:choose>
		<c:when test="${systemUser.role == 'admin'}">
			<% System.out.println("-> admin"); %>
			<c:if test="${events != null}">
				<h1>
					Groups
					<c:if test="${id_event != 0}">for event number ${id_event}</c:if>
				</h1>
				    Choose an Event: 
				    <select>
					<option selected></option>
					<c:forEach items="${events}" var="event">
						<c:url
							value="/private/groupList.html?action=listRecord&id_event=${event.id}"
							var="url" />
						<option value="${event.id}"
							onClick="window.location.href='${url}'">${event.name}</option>
					</c:forEach>
				</select>
			</c:if>
		</c:when>
		<c:when test="${systemUser.role == 'event_mng'}">
			<% System.out.println("-> event_mng"); %>
			<c:if test="${events != null}">
				<h1>
					Groups
					<c:if test="${id_event != 0}">for event  ${id_event}</c:if>
				</h1>
				    Choose an Event: 
				    <select>
					<option selected></option>
					<c:forEach items="${events}" var="event">
						<c:url
							value="/private/groupList.html?action=listRecord&id_event=${event.id}"
							var="url" />
						<option value="${event.id}"
							onClick="window.location.href='${url}'">${event.id} -
							${event.name}</option>
					</c:forEach>
				</select>
			</c:if>
		</c:when>
		<c:when test="${systemUser.role == 'group_mng' }">
			<% System.out.println("-> group_mng"); %>
			<h1>Yours Groups</h1>
		</c:when>
	</c:choose>

	<table border=1>
		<thead>
			<tr>
				<th>Group Id</th>
				<th>Event Id</th>
				<th>Group Name</th>
				<th>Group referent Id</th>
				<th>Max Group Number</th>
				<!-- <th>Blocked</th> -->
				<th colspan=3>Action</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${records}" var="record">
				<tr>
					<td>${record.id}</td>
					<td>${record.id_event}</td>
					<td>${record.name}</td>
					<td>${record.id_group_referent}</td>
					<td>${record.max_group_number}</td>
					<!-- <td>${record.blocked}</td> -->
					<td><c:if
							test="${(systemUser.role == 'admin') || (systemUser.role == 'event_mng') }">
							<a
								href="<c:url value='/private/group.jsp?action=edit&id=${record.id}&id_event=${record.id_event}'/>">Update</a>
						</c:if></td>
					<td><c:if
							test="${(systemUser.role == 'admin') || (systemUser.role == 'event_mng') }">
							<a
								href="<c:url value='/private/groupDelete?action=delete&id=${record.id}&id_event=${record.id_event}'/>"
								onclick="return confirmDelete();">Delete</a>
						</c:if></td>
					<td><a
						href="<c:url value='/private/participantList.html?action=listRecord&id_group=${record.id}'/>">Participants</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if
		test="${(id_event != 0) &&(systemUser.role != 'group_mng'  || systemUser.role != 'admin') }">
		<p>
			<a href="group.jsp?action=insert&id_event=${id_event}">Add Group</a>
		</p>
	</c:if>
</body>
</html>