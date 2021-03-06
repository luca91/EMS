<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<%
System.out.println("index.html");
%>
<html lang="en">
<head>
<title>EMS - Enrollment Management System - Private Portal</title>
<meta charset="utf-8">
<link rel="stylesheet" href="../css/reset.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="../css/tables_style.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="../css/style_portal.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="../css/layout.css" type="text/css"
	media="screen">
<script type="text/javascript" src="../js/jquery-1.6.min.js"></script>
<script src="../js/cufon-yui.js" type="text/javascript"></script>
<script src="../js/cufon-replace.js" type="text/javascript"></script>
<script src="../js/Open_Sans_400.font.js" type="text/javascript"></script>
<script src="../js/Open_Sans_Light_300.font.js" type="text/javascript"></script>
<script src="../js/Open_Sans_Semibold_600.font.js"
	type="text/javascript"></script>
<script src="../js/FF-cash.js" type="text/javascript"></script>
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<!--[if lt IE 7]>
	<div style=' clear: both; text-align:center; position: relative;'>
		<a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/images/upgrade.jpg" border="0"  alt="" /></a>
	</div>
<![endif]-->
<!--[if lt IE 9]>
	<script type="text/javascript" src="js/html5.js"></script>
	<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
<![endif]-->
<script type="text/javascript">
$(document).ready(function(){
	  $("#btn1").click(function(){
	    $("#ajax-parse").append("<b>Appended text</b>.");
	  });
	  
	  $(".p2").click(function(){
	  	$("#ajax-parse").load("eventList.html");
	  });	  
	  
	  $(".p3").click(function(){
	  	$("#ajax-parse").empty();
	  });
});
</script>
</head>
<body id="page5">
	<div class="bg">
		<div class="main">
			<!-- HEADER -->
			<header>
				<!-- TOP + LOGO -->
				<div class="row-1">
					<h1>
						<a class="logo" href="index.html">EMS</a> <strong class="slog">Enrollment
							Management System</strong>
					</h1>
					<form id="session-id">
						Welcome - ${systemUser.email}<br> Role: ${systemUser.role}<br>
						Time:
						<jsp:useBean id="today" class="java.util.Date" scope="page" />
						<fmt:formatDate value="${today}" pattern="dd MMM yyyy - HH:mm" />
					</form>
				</div>
				<!-- MENU -->
				<div class="row-2">
					<nav>
						<ul class="menu">
							<c:import url="inc/header.jsp" />
						</ul>
					</nav>
				</div>
			</header>
			<!-- CONTENT -->
			<section id="content">
				<div class="padding">
					<div class="wrapper margin-bot" id="ajax-parse">
						<!-- AJAX CONTENT -->
						<div class="col-3">
							<div class="indent">
								<h3 class="p0">Enrollment Management System - private site</h3>
								<h3 class="p0">
									<a href="#">Homepage</a>
								</h3>
								<h3 class="p2">
									<a href="#">TEST - load content</a>
								</h3>
								<h3 class="p3">
									<a href="#">TEST - delete content</a>
								</h3>
								<button id="btn1">Append text</button>
								<!-- TEST -->
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
											<th colspan=3>Action</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>"${record.id}"</td>
											<td>"${record.id_manager}"</td>
											<td>"${record.name}"</td>
											<td>"${record.description}"</td>
											<td>"${record.start}"</td>
											<td>"${record.end}"</td>
											<td>"${record.enrollment_start}"</td>
											<td>"${record.enrollment_end}"</td>
											<td><a
												href='/private/event.jsp?action=edit&id=${record.id}'>Update</a></td>
											<td><a
												href='/private/eventDelete?action=delete&id=${record.id}'
												onclick="return confirmDelete();">Delete</a></td>
											<td><a
												href='/private/groupList.html?action=listRecord&id_event=${record.id}'>Groups</a></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
	<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>