package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LoginBean;
import dbUtilities.dbUtility;

@WebServlet("/loginCheck")
public class loginCheck extends HttpServlet{
	
	@SuppressWarnings({ "unchecked", "null" })
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		dbUtility db = new dbUtility();
		
		LoginBean loginBean = new LoginBean();
		String loginID = request.getParameter("loginID");
		
		String serverError = db.tryConnection();
		if(!checkCookieEnable(request, response)){
			loginBean.setError("Please enable cookies.");
			request.getSession().setAttribute("cookieError", loginBean);
			RequestDispatcher rd = request.getRequestDispatcher("/MyErrorPage.jsp");
			rd.forward(request, response);
		}else if(serverError != null){
			if(serverError.equals("Unable to load driver.")){
				loginBean.setError("Unable to load driver.");
				request.getSession().setAttribute("serverError", loginBean);
				RequestDispatcher rd = request.getRequestDispatcher("/MyErrorPage.jsp");
				rd.forward(request, response);
			}else{
				loginBean.setError("Unable to establish connection.");
				request.getSession().setAttribute("serverError", loginBean);
				RequestDispatcher rd = request.getRequestDispatcher("/MyErrorPage.jsp");
				rd.forward(request, response);
			}
		}
		
		if(loginID.length() !=9 || !isStringInt(loginID)){
			loginBean.setError("Error: ID must be a 9 digit number");
			request.getSession().setAttribute("loginError", loginBean);
			RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}else{
			if(db.checkID(loginID)){
				addIdCookie(loginID, response);
				loginBean.setId(loginID);
				request.getSession().setAttribute("getLoginId", loginBean);
				RequestDispatcher rd = request.getRequestDispatcher("/MyCourses.jsp");
				rd.forward(request, response);
			}else{
				loginBean.setError("Error: invalid ID");
				request.getSession().setAttribute("loginError", loginBean);
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
			}
		}
	
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public boolean checkCookieEnable(HttpServletRequest request, HttpServletResponse response){
		boolean cookieEnable = false;
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i=0; i<cookies.length; i++){
				if(cookies[i].getName().equals("test_cookie_enable")){
					cookieEnable = true;
				}
			}
		}
		return cookieEnable;
	}
	
	public boolean isStringInt(String s){
		try{
			Integer.parseInt(s);
			return true;
		}catch (NumberFormatException e){
			return false;
		}
	}
	
	public void addIdCookie(String id, HttpServletResponse response){
		Cookie userId = new Cookie("login_user_id", id);
		userId.setMaxAge(60*60*24);
		response.addCookie(userId);
	}
	
}
