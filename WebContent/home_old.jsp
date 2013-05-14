<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ems.controller.LoginController, com.ems.controller.UserController" %>
<%
	//Session handling
	LoginController user = new LoginController();

	if(user.checkValidity() == false)
	{
		session.invalidate();
%>
		<jsp:forward page="InvalidSession.jsp" />
<%
	}
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EMS - Welcome to the EMS Management Page</title>
<link rel="stylesheet" type="text/css" href="css/tabs.css" />
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
</head>
<body>
<p>Welcome - ${username} - <a href="LogoutController" >logout</a></p>
<ul class="css-tabs">
<c:choose>
	<c:when test="${username.role=='admin'}" >		
		<li><a id="t1" href="#tab1">Admin</a></li>
		<li><a id="t2" href="#tab2">Event Management</a></li>
		<li><a id="t3" href="#tab3">Group Management</a></li>
		<li><a id="t4" href="#tab4">Participants</a></li>	
	</c:when>
	<c:when test="${username.role=='event_mng'}" >		
		<li><a id="t1" href="#tab1">Event Management</a></li>
		<li><a id="t2" href="#tab2">Group Management</a></li>
		<li><a id="t3" href="#tab3">Participants</a></li>
	</c:when>
	<c:when test="${username.role=='group_mng'}" >		
		<li><a id="t1" href="#tab1">Participants</a></li>
	</c:when>
	<c:otherwise>		
		<li><a id="t1" href="#tab1">ERROR</a></li>
	</c:otherwise>
</c:choose>	
</ul>
<div class="css-panes">
	<!-- Admin pane -->
	<div>
	<p><a href="UserController?action=listUser">Users</a></p><!-- call listUser.jsp -->
	<p><a href="#">Web Pages editing</a></p>
	<p><a href="#">Settings</a></p>
	</div>
	<!-- Event Management pane -->
	<div>
	<p>Events</p><!-- here an event manager should not be able to write on other Event Manager's events -->
	<p>Group Referents</p>
	<p>Tickets</p>
	</div>
	<!-- Group Management pane -->
	<div>
	<p>Groups</p>
	</div>
	<!-- Participants pane -->
	<div>
	<p>Participants</p>
	</div>
</div>
<!-- activate tabs with JavaScript -->
<script>
  $(function() {
  // :first selector is optional if you have only one tabs on the page
  $(".css-tabs:first").tabs(".css-panes:first > div");
  });
</script>
</body>
</html>