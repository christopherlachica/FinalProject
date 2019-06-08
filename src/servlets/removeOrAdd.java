package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CourseBean;
import beans.LoginBean;
import dbUtilities.dbUtility;

@WebServlet("/removeOrAdd")
public class removeOrAdd extends HttpServlet{

	@SuppressWarnings({ "unchecked", "null" })
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		
		String userAction = request.getParameter("removeOrAdd");
		String courseId = request.getParameter("courseRadio");
		CourseBean cBean = new CourseBean();
		
		if(userAction.equals("Remove")){
			if(!(courseId == null)){
				cBean.setCourseId(courseId);
				request.getSession().setAttribute("getCourseId", cBean);
				RequestDispatcher rd = request.getRequestDispatcher("/ConfirmRemove.jsp");
				rd.forward(request, response);
			}else{
				cBean.setError("Please select a course before clicking on delete");
				request.getSession().setAttribute("getCourseError", cBean);
				RequestDispatcher rd = request.getRequestDispatcher("/MyCourses.jsp");
				rd.forward(request, response);
			}
		}else{
			response.sendRedirect("AddCourse.jsp");
		}
	
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
