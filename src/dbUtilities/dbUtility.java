package dbUtilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LoginBean;
import servlets.checkCookie;

public class dbUtility{
	private static String DB_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String DB_CONNECTION = "jdbc:sqlserver://s16988308.onlinehome-server.com:1433;databaseName=CUNY_DB;integratedSecurity=false;";
	private static String DB_USER = "cst3613";
	private static String DB_PASS = "password1";
	private static Connection connection;
	private static String USER_SSN;
	
	public String tryConnection(){
		String serverError = null;
		try {
			// Load the JDBC driver
			Class.forName(DB_CLASS);
			
			// Connect to a database
			connection = DriverManager.getConnection
					(DB_CONNECTION, DB_USER, DB_PASS);
		} catch (ClassNotFoundException e) {
			serverError = "Unable to load driver.";
		} catch (SQLException e) {
			serverError = "Unable to establish connection.";
		}
		try {
			connection.close();
		} catch (SQLException e) {
			
		}
		return serverError;
	}
	
	public void startConnection() {
		try {
			// Load the JDBC driver
			Class.forName(DB_CLASS);
			
			// Connect to a database
			connection = DriverManager.getConnection
					(DB_CONNECTION, DB_USER, DB_PASS);
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load driver");
		} catch (SQLException e) {
			System.out.println("Unable to establish connection");
		}
	}
	
	public boolean checkID(String ssn){
		boolean loggedIn = false;
		startConnection();
		String sql = "SELECT * FROM Students WHERE ssn=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, ssn);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				USER_SSN = ssn;
				loggedIn = true;
			}
			ps.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loggedIn;
	}
	
	public String getCourseList(){
		String result = "";
		String course = "";
		int credit = 0;
		startConnection();
		String sql = "SELECT Enrollment.courseId, Course.numOfCredits FROM Enrollment, "
				+ "Course WHERE Enrollment.ssn=? AND Enrollment.courseId=Course.courseID";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, USER_SSN);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				course = rs.getString(1);
				credit = credit + rs.getInt(2);
				result = result + 
						"<tr><td>" + course + "</td><td>" +
						"<input type='radio' name='courseRadio' value='" +
						course + "'/></td></tr>";
			}
			ps.close();
			rs.close();
			connection.close();
		} catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = result + "</table></div><br>Total Number of Credits: " + credit;
		return result;
	}
	
	public void deleteCourse(String courseId, String ssn){
		startConnection();
		String sql = "DELETE FROM Enrollment WHERE courseId = ? AND ssn = ?";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, courseId);
			ps.setString(2, ssn);
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getAvailableCourses(String ssn){
		startConnection();
		String sql = "SELECT courseId FROM Course Where courseID "
				+ "NOT IN (SELECT courseId FROM Enrollment WHERE ssn=?)";
		String result = "<option value=''></option>";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, ssn);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String courseId = rs.getString(1);
				result = result + "<option value='" + courseId + "'>"
						 + courseId + "</option>";
			}
			ps.close();
			rs.close();
			connection.close();
		} catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public void addCourse(String courseId, String ssn){
		startConnection();
		String sql = "INSERT INTO Enrollment VALUES(?, ?, GETDATE(), 'A')";
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, ssn);
			ps.setString(2, courseId);
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
