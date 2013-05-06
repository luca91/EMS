<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EMS - Event Request</title>
<script src="scripts/eventrequestvalidation.js"></script>
</head>
<body>
<form name="eventRequest" method="post">
<table align="center" border="1">
  <tr>
    <td colspan="2" align="center"><b>Event Request Form</b><br></td>
  </tr>
  <tr>
    <td><b>Event Name</b></td>
    <td><input type="text" name="eventname"></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
     <textarea name="eventdescription" rows="5" cols="32">Description of the event</textarea>
    </td>
  </tr>
  <tr>
    <td><b>Location</b></td>
    <td><input type="text" name="location"></td>
  </tr>
  <tr>
    <td><b>Start date (dd/mm/yyyy)</b></td>
    <td><input type="text" name="startdate"></td>
  </tr>
  <tr>
    <td><b>End date (dd/mm/yyyy)</b></td>
    <td><input type="text" name="enddate"></td>
  </tr>
  <tr>
    <td><b>Enrollment start (dd/mm/yyyy)</b></td>
    <td><input type="text" name="enrollmentstart"></td>
  </tr>
  <tr>
    <td><b>Enrollment end (dd/mm/yyyy)</b></td>
    <td><input type="text" name="enrollmentend"></td>
  </tr>
  <tr>
    <td><b>Max attendants (#)</b></td>
    <td><input type="text" name="maxattendants"></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
     <input type="button" value="Submit Event Request" onClick="eventReqValidation()">
    </td>
  </tr>
</table>
</form>
</body>
</html>