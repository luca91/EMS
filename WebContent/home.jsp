<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EMS - Welcome to the EMS Management Page</title>
<link rel="stylesheet" type="text/css" href="css/tabs.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
<p>Welcome - <!-- username : logout --></p>
<ul class="css-tabs">
	<!-- 
	<c:if test="" { }  >
	
	carica tab
		elenco attività della tab
	
	</c:if>
	 -->
	<li><a id="t1" href="#tab1">Admin</a></li>
	<li><a id="t2" href="#tab2">Event Management</a></li>
	<li><a id="t3" href="#tab3">Group Management</a></li>
	<li><a id="t4" href="#tab4">Participants</a></li>
</ul>

<div class="css-panes">
	<!-- Admin pane -->
	<div>
	<p>Users</p><!-- call listUser.jsp -->
	<p>Web Pages editing</p>
	<p>Settings</p>
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
  $(".css-tabs:first").tabs(".css-panes:first > div");
  });
</script>

</body>
</html>