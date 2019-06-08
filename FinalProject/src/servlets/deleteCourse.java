package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbUtilities.dbUtility;

@WebServlet("/deleteCourse")
public class deleteCourse extends HttpServlet{

	@SuppressWarnings({ "unchecked", "null" })
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		
		String userAction = request.getParameter("removeConfirmation");
		String courseId = request.getParameter("course");
		String ssn = checkCookie.getIdCookie(request);
		
		dbUtility db = new dbUtility();
		
		if(userAction.equals("Yes")){
			db.deleteCourse(courseId, ssn);
		}
		
		response.sendRedirect("MyCourses.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
