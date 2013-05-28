<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<h3>Events</h3> 
<table id="box-table-a">
	<thead>
		<tr>
			<th scope="col">Event Id</th>
			<th scope="col">Manager Id</th>
			<th scope="col">Event Name</th>
			<th scope="col">Description</th>
			<th scope="col">Start</th>
			<th scope="col">End</th>
			<th scope="col">Enrollment_start</th>
			<th scope="col">Enrollment_end</th>
			<th scope="col" colspan=3>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:if
			test="${systemUser.role == 'admin' || systemUser.role == 'event_mng'}">
			<c:forEach items="${records}" var="record">
				<tr>
					<td>${record.id}</td>
					<td><c:out value="${record.id_manager}"></c:out></td>
					<td><c:out value="${record.name}"></c:out></td>
					<td><c:out value="${record.description}"></c:out></td>
					<td><c:out value="${record.start}"></c:out></td>
					<td><c:out value="${record.end}"></c:out></td>
					<td><c:out value="${record.enrollment_start}"></c:out></td>
					<td><c:out value="${record.enrollment_end}"></c:out></td>
					<td><a
						href="<c:url value='/private/event.jsp?action=edit&id=${record.id}'/>">Update</a></td>
					<td><a
						href="<c:url value='/private/eventDelete?action=delete&id=${record.id}'/>"
						onclick="return confirmDelete();">Delete</a></td>
					<td><a
						href="<c:url value='/private/groupList.html?action=listRecord&id_event=${record.id}'/>">Groups</a></td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<hr>