<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Course List</title>
</head>
<body>
<h2>My Course List</h2>
<%@ taglib uri="/WEB-INF/myTags.tld" prefix="mytags" %>
	<form action="removeOrAdd" method="post">
		<div style="height:150px; width:180px; overflow:auto">
			<table border="1">
			<mytags:courses/>
		<br><br>
		<input type="submit" name="removeOrAdd" value="Remove"/>
		<input type="submit" name="removeOrAdd" value="Add"/>
	</form>
	<br>
	<jsp:useBean id="getCourseError" class="beans.CourseBean" scope="session"/>
	<font color="red"><jsp:getProperty name="getCourseError" property="error"/></font>
</body>
</html>