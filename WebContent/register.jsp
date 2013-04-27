<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EMS - Enrollment Management System - Registration</title>
</head>
<body>
<!-- ask administrator for registration
not everybody allowed to register
 -->
 <form name="registrationForm" action="UserRegistration" method="POST"><!-- TO DO: handle encryption -->
First Name:<input type="text" name="fname"><br>
Last Name:<input type="text" name="lname"><br>
Date of birth(yyyy-mm-dd):<input type="text" name="date_of_birth"><br>
E-mail:<input type="text" name="username"><br>
Password:<input type="password" name="password"><br>
Role:<select id="role" name="role">
<option value="eventManager">Event Manager</option>
<option value="groupReferent">Group Referent</option>
</select>
<input type="submit" value="Register">
</form>
<!-- check that all fields are fulfilled -->
</body>
</html>