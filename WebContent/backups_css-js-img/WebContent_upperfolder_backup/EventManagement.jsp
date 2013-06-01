<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Event Management Page</title>
</head>
<body>
<h1>Edit event information</h1>
<!-- DropDownBox with existing events -->
<form action="" method="post">
Event name: <input type="text" name="eventname"><br>
Description: <input type="text" name="description"><br>
Location: <input type="text" name="location"><br>
Start Date: <input type="text" name="startdate"><br>
End Date: <input type="text" name="enddate"><br>
Enrollment Start Date: <input type="text" name="enrollmentstart"><br>
Enrollment Deadline: <input type="text" name="enrollmentend"><br>
Max attendants: <input type="text" name="maxattendants"><br>
<input type="submit" value="edit">
</form>
</body>
</html>