<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Badge download</title>
</head>
<body>
<h3>Badges</h3> 
		<c:choose>
			<c:when test="${id_group != 0}">
				<h1>Badges <c:if test="${id_group != 0 }">for Group: ${id_group}</c:if></h1>	
			</c:when>
		</c:choose>
		
		Choose a group
		<select>
			<option selected></option> 
	    	<c:forEach items="${groups}" var="group">
	    		<c:url value="/private/badgeList.html?action=listRecord&id_group=${group.id}" var="url"/>
	    		<option value="${url}">${group.id}</option>
	    	</c:forEach>
	    </select>
	   
	  	<c:set var="act">
	  		<c:url value="/private/downloadBadge?action=download&id_participant=${id}" /> 
	  	</c:set> 
	  
	    <form method="POST" action="${act}" name="downloadBadge">
		    <table id="box-table-a">
		        <thead>
		            <tr>
		                <th scope="col">Participant</th>
		                <th scope="col">First Name</th>
		                <th scope="col">Last Name</th>             	                	                
		                <th scope="col">Badge</th>
		            </tr>
		        </thead>		              
		        <tbody>
			            <c:forEach items="${records}" var="record">
			                <tr>
			                    <td>${record.id}</td>
			                    <td>${record.fname}</td>
			                    <td>${record.lname}</td>        	                    	                    	                    
			                    <td><a href="<c:url value='/private/downloadBadge?action=download&id=${record.id}&id_group=${id_group}'/>">Generate</a></td>
			                </tr>
			            </c:forEach>
			         
		        </tbody>
		    </table>
	    </form>
	    
</body>
</html>