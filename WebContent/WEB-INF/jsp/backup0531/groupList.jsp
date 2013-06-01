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
<h3>Groups</h3>
<!-- DROPDOWN BOX SELECTION -->
<c:choose>
	<c:when test="${systemUser.role == 'admin'}">
		<c:if test="${events != null}">
			<!-- ??? -->
				<c:if test="${id_event != 0}">for event number ${id_event}</c:if>
			    Choose an Event: 
			    <select>
				<option selected></option>
				<c:forEach items="${events}" var="event">
					<c:url value="/private/groupList.html?action=listRecord&id_event=${event.id}" var="url" />
					<option value="${event.id}"	onClick="window.location.href='${url}'">${event.name}</option>
				</c:forEach>
			</select>
		</c:if>
	</c:when>
	<c:when test="${systemUser.role == 'event_mng'}">
		<c:if test="${events != null}">
			<h3>Groups</h3>
				<c:if test="${id_event != 0}">for event  ${id_event}</c:if>
			    Choose an Event:
			    <select>
				<option selected></option>
				<c:forEach items="${events}" var="event">
					<c:url  value="/private/groupList.html?action=listRecord&id_event=${event.id}" var="url" />
					<option value="${event.id}"
						onClick="window.location.href='${url}'">${event.id} -
						${event.name}</option>
				</c:forEach>
			</select>
		</c:if>
	</c:when>
	<c:when test="${systemUser.role == 'group_mng' }">
		<h3>Yours Groups</h3><!-- ??? -->
	</c:when>
</c:choose>
<!-- TABLE -->
<table id="box-table-a">
	<thead>
		<tr>			
			<th scope="col">Group Id</th>
			<th scope="col">Event Id</th>
			<th scope="col">Group Name</th>
			<th scope="col">Group referent Id</th>
			<th scope="col">Max Group Number</th>
			<!-- <th scope="col">Blocked</th> -->
			<th scope="col" colspan=3>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${systemUser.role == 'admin' || systemUser.role == 'event_mng'}">
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
		</c:if>
	</tbody>
</table>
<hr>
<c:if
		test="${(id_event != 0) &&(systemUser.role != 'group_mng'  || systemUser.role != 'admin') }">
		<p>
			<a href="group.jsp?action=insert&id_event=${id_event}">Add Group</a>
		</p>
</c:if>