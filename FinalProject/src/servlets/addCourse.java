package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbUtilities.dbUtility;

@WebServlet("/addCourse")
public class addCourse extends HttpServlet{

	@SuppressWarnings({ "unchecked", "null" })
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String confirmation = request.getParameter("addCourseConfirm");
		String courseId = request.getParameter("courses");
		dbUtility db = new dbUtility();
		
		if(confirmation.equals("Add")){
			db.addCourse(courseId, checkCookie.getIdCookie(request));
		}
		response.sendRedirect("MyCourses.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
