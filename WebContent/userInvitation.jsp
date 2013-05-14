<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>EMS - Enrollment Management System - User Invitation</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="screen">
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
<script src="js/cufon-yui.js" type="text/javascript"></script>
<script src="js/cufon-replace.js" type="text/javascript"></script>
<script src="js/Open_Sans_400.font.js" type="text/javascript"></script>
<script src="js/Open_Sans_Light_300.font.js" type="text/javascript"></script> 
<script src="js/Open_Sans_Semibold_600.font.js" type="text/javascript"></script> 
<script src="js/FF-cash.js" type="text/javascript"></script>  
<script src="http://cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
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
<!-- header -->
	<div class="bg">
		<div class="main">
			<header>
				<div class="row-1">
					<h1>
						<a class="logo" href="index.html">EMS</a>
						<strong class="slog">Enrollment Management System</strong>
					</h1>					
				</div>
				<!-- Menu -->
				<div class="row-2">
					<nav>
						<ul class="menu">										
						  <li><a id="t1" href="userInvitation.jsp">User Invitation</a></li>						  					  
						</ul>
					</nav>
				</div>
				<!-- -->				
			</header>
<!-- content -->
			<section id="content">
				<div class="padding">
					<div class="wrapper margin-bot">
						<div class="col-3">
							<div class="indent">
								<br>								
								<h4>New User</h4><br>
								<form id="contact-form" action="" method="post" enctype="multipart/form-data">
									<fieldset>
										<label><span class="text-form">Email:</span><input name="p1" type="text" /></label>
										<label><span class="text-form">First Name:</span><input name="p2" type="text" /></label>
										<label><span class="text-form">Last Name:</span><input name="p3" type="text" /></label>										 
										<label>
											<span class="text-form">Role:</span>
												<select id="role">
									               <option value = "event_mng">Event Manager</option>
									               <option value = "group_referent">Group Referent</option>
									            </select>
										</label>										              
									</fieldset>						
								</form>					
							</div>							
						</div>
						
					</div>
					<a class="button-2" href="#" onClick="">Send invitation</a>
					<a class="button-2" href="index.jsp" onClick="">Cancel</a>				
				</div>				
			</section>			
		</div>
	</div>
	<script type="text/javascript"> Cufon.now(); </script>
</body>
</html>
