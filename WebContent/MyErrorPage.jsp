<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error!!!</title>
</head>
<body>
	<br>
	<h3>Error:</h3><br>
	<jsp:useBean id="cookieError" class="beans.LoginBean" scope="session"/>
	<font color="red"><jsp:getProperty name="cookieError" property="error"/></font>
	<br>
	<jsp:useBean id="serverError" class="beans.LoginBean" scope="session"/>
	<font color="red"><jsp:getProperty name="serverError" property="error"/></font>
	<br>
	<a href="Login.jsp">Back to Login</a>
</body>
</html>