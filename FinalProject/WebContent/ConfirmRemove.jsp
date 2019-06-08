<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="getCourseId" class="beans.CourseBean" scope="session"/>
	<form action="deleteCourse" method="post">
	<h2>Removing <br> <jsp:getProperty name="getCourseId" property="courseId"/> ?</h2>
	<br>
	<input type="hidden" name="course" 
		value="<jsp:getProperty name="getCourseId" property="courseId"/>" />
	<input type="submit" name="removeConfirmation" value="Yes" />
	<input type="submit" name="removeConfirmation" value="No" />
	</form>
</body>
</html>