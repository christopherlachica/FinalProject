<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="servlets.checkCookie" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<%checkCookie.addConfirmationCookie(response); %>
	<jsp:useBean id="loginError" class="beans.LoginBean" scope="session"/>
	<font color="red"><jsp:getProperty name="loginError" property="error"/></font>
	<form action="loginCheck" method="POST">
		<br>
		ID: <input type="text" name="loginID" 
			value="<%= checkCookie.getIdCookie(request) %>" />
		<br><br>
		<input type="submit" value="Login">
	</form>
</body>
</html>