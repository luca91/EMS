<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
		<td><jsp:useBean id="today" class="java.util.Date" scope="page" />
			<fmt:formatDate value="${today}" pattern="dd MMM yyyy - HH:mm" /></td>
	</tr>
</table>