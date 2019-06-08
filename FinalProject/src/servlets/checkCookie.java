package servlets;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class checkCookie extends HttpServlet{
	
	public static String getIdCookie(HttpServletRequest request){
		String id = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i=0; i<cookies.length; i++){
				if(cookies[i].getName().equals("login_user_id")){
					id = cookies[i].getValue();
				}
			}
		}
		return id;
	}
	
	public static void addConfirmationCookie(HttpServletResponse response){
		Cookie test = new Cookie("test_cookie_enable", "yes");
		test.setMaxAge(60*60*24);
		response.addCookie(test);
	}
	
}
