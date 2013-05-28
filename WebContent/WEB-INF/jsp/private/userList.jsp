<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<h3>Users</h3>
<table id="box-table-a">
	<thead>
		<tr>			
			<th scope="col">User Id</th>
			<th scope="col">User Role</th>
			<th scope="col">First Name</th>
			<th scope="col">Last Name</th>
			<th scope="col">Email</th>
			<th scope="col" colspan=2>Action</th>
		</tr>
	</thead>
	<tbody>
		<c:if
			test="${systemUser.role == 'admin'}">
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.id}"></c:out></td>
					<td><c:out value="${user.role}"></c:out></td>
					<td><c:out value="${user.fname}"></c:out></td>
					<td><c:out value="${user.lname}"></c:out></td>
					<td><c:out value="${user.email}"></c:out></td>
					<td><a
						href="#">Update</a></td>
					<td><a
						href="#">Delete</a></td>					
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<hr>