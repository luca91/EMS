<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css"
	    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
	<title>Group form</title>
	</head>
	<body>
		<c:import url="inc/header.jsp"/>
		
		<c:set var="act">
			<c:url value="/private/groupAdd?action=recordList" /> 
		</c:set>
	
	    <form method="POST" action="${act}" name="frmAddGroup">
	        Group ID : <input type="text" readonly="readonly" name="id"
	            value="${record.id}" />  - TO BE HIDDEN <br /> 
	        Event ID : <input
	            type="text" name="id_event" readonly="readonly"
	            value="${id_event}" />  - TO BE HIDDEN<br /> 
	        Group Name: <input
	            type="text" name="name"
	            value="${record.name}" /> <br /> 	            
	        Group referent :
       	        <select name="id_group_referent">
          				<c:forEach items="${listOfGroup_mng}" var="options">	               
               			<option value="${options.id }" 
               				<c:if test="${options.id == record.id_group_referent }">selected</c:if> 
               			>${options.id } - ${options.fname} ${options.lname }</option>
           			</c:forEach>
           		</select> 
	        	<br /> 
	        max_group_number : <input
	            type="text" name="max_group_number"
	            value="${record.max_group_number}" /> <br /> 	            
	        <input type="hidden" name="blocked"
	            value="${record.blocked}" /> <br />           
	        <input type="submit" value="Submit" />
	        <input type="button" value="Back" onClick="history.go(-1);return true;"/>
        	<c:if test="${param.id eq null }">
        		<input type="reset" value="Reset"/>
        	</c:if>	        
	    </form>
	</body>
</html>