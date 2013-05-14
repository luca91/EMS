<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Show All Participants</title>
	</head>
<script>
function confirmSend(list){
	var a = new Array();
	a = list.split(";");
	
	var msg ="You are sending invitation via mail to the following addresses: \n\n";
	
	for(i=0;i<a.length;i++){
		
		msg += "- " + a[i] + " \n";
		//alert(a[i]);
    }
	
	if(confirm(msg)){
		return true;
	}
	else {
		document.getElementById("frmInviteParticipan").reset();
		return false;
	}
}
</script>


	<body>
		<table>
			<tr>
				<td>email:</td>
				<td>${sessionScope.systemUser.email}</td>
			</tr>
			<tr>
				<td>role:</td>
				<td>${sessionScope.systemUser.role}</td>
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
		<a href='<c:url value="/private/groupList.html"/>'>Groups Management</a> |
		<a href='<c:url value="/private/participantList.html"/>'>Participants Management</a>
		<hr/>

		<h1>Participants for id_group = ${id_group}</h1>

	    Choose a Group:
	    <select>
	    	<option selected></option>
	    	<c:forEach items="${groups}" var="group">
	    		<c:url value="/private/participantList.html?action=listRecord&id_group=${group.id}" var="url"/>
	    		<option value="${group.id}" onClick="window.location.href='${url}'">${group.id}</option>
	    	</c:forEach>
	    </select>
	    
	    <br/>
	    
	    <c:set var="act">
			<c:url value="/private/participantApprove?action=approve&id_group=${id_group}" /> 
		</c:set>
	    <form method="POST" action="${act}" name="frmApproveParticipan">
		    <table border=1>
		        <thead>
		            <tr>
		                <th>Participant Id</th>
		                <th>Group Id</th>
		                <th>First Name</th>
		                <th>Last Name</th>
		             	<th>Email</th>
		                <th>Date of birth</th>
		                <th>Registration Date</th>
		                <th>Approved</th>
		                <th>Blocked</th>                	                	                
		                <th colspan=2>Action</th>
		            </tr>
		        </thead>
		              
		        <tbody>
	
			            <c:forEach items="${records}" var="record">
			                <tr>
			                    <td>${record.id}</td>
			                    <td>${record.id_group}</td>
			                    <td>${record.fname}</td>
			                    <td>${record.lname}</td>
			                    <td>${record.email}</td>			                    
			                    <td>${record.date_of_birth}</td>
			                    <td>${record.registration_date}</td>  
			                   	<td>${record.approved}</td>  
			                   	<td>${record.blocked}</td>                 	                    	                    	                    
			                    <td><a href="<c:url value='/private/participant.jsp?action=edit&id=${record.id}&id_group=${id_group}'/>">Update</a></td>
			                    <td><a href="<c:url value='/private/participantDelete?action=delete&id=${record.id}&id_group=${id_group}'/>">Delete</a></td>
			                </tr>
			            </c:forEach>
			         
		        </tbody>
		    </table>
		    
		    <input type="submit" value="Approve All" /><br />
		    
	    </form>
	    
	    <p><a href="participant.jsp?action=insert&id_group=${id_group}">Add Participant</a></p>
	    <br />
	    
	    <c:if test="${id_group != 0  }">
		   	<c:set var="act">
				<c:url value="/private/participantInvite?action=invite&id_group=${id_group}" /> 
			</c:set>
		  	 <form method="POST" action="${act}" id="frmInviteParticipan" name="frmInviteParticipan" onsubmit="return confirmSend(frmInviteParticipan.elements['listTo'].value);">
		  	 	<fieldset>
	    			<legend>Invite participants</legend>
			  	 	Email : <input type="text" name="listTo" /> 
			  	 	<c:if test="${showCount == 'y' }">
			  	 		${count} email sent!
			  	 	</c:if>
			  	 	<br />
			  	 	<input type="submit" value="Invite"  />
			  	 	<input type="reset"/>
		  		</fieldset>
		  	 </form>
	  	 </c:if>

	  
	</body>
</html>