<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<%
System.out.println("participants.html");
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
<script src="../js/ajax-section.js" type="text/javascript"></script>
<!--[if lt IE 7]>
	<div style=' clear: both; text-align:center; position: relative;'>
		<a href="http://www.microsoft.com/windows/internet-explorer/default.aspx?ocid=ie6_countdown_bannercode"><img src="http://www.theie6countdown.com/images/upgrade.jpg" border="0"  alt="" /></a>
	</div>
<![endif]-->
<!--[if lt IE 9]>
	<script type="text/javascript" src="js/html5.js"></script>
	<link rel="stylesheet" href="css/ie.css" type="text/css" media="screen">
<![endif]-->
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
					<div class="wrapper margin-bot">
						<!-- AJAX CONTENT -->
						<div class="col-3" id="ajax-parse">
							<div class="indent">
								<h3 class="p0">
									<a href="#">List participants</a>
								</h3>
								<br>
								<h3 class="p0">
									<a href="#">Add participant(s)</a>
								</h3>
								<br>
								<h3 class="p0">
									<a href="#">Update participant(s)</a>
								</h3>
								<br>
								<h3 class="p0">
									<a href="#">Delete Participant(s)</a>
								</h3>
								<br>
								<h3 class="p0">
									<a href="#">Print tickets(PDF)</a>
								</h3>
								<br>
								<!-- TEST -->
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