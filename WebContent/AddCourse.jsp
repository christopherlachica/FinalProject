<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="servlets.checkCookie" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a Course</title>
</head>
<body>
	<h2>Select a Course</h2>
	<br>
	<jsp:useBean id="addCourses" class="beans.CourseBean"/>
	<jsp:setProperty name="addCourses" property="ssn"
		value="<%= checkCookie.getIdCookie(request) %>" />
	<form action="addCourse" method="post">
		<select name="courses">
    		<jsp:getProperty name="addCourses" property="availableCourse"/>
		</select>
		<input type="submit" name="addCourseConfirm" value="Add"/>
		<input type="submit" name="addCourseConfirm" value="Cancel"/>
	</form>
</body>
</html>