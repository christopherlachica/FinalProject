package beans;

import dbUtilities.dbUtility;

public class CourseBean {
	
	String courseId = "";
	String error = "";
	String ssn = "1";
	String availableCourse = "";
	
	public String getCourseId(){
		return courseId;
	}
	
	public void setCourseId(String cId){
		this.courseId = cId;
	}
	
	public String getError(){
		return error;
	}
	
	public void setError(String error){
		this.error = error;
	}
	
	public String getSsn(){
		return ssn;
	}
	
	public void setSsn(String ssn){
		this.ssn = ssn;
	}
	
	public String getAvailableCourse(){
		dbUtility db = new dbUtility();
		availableCourse = db.getAvailableCourses(getSsn());
		return availableCourse;
	}
	
	public void setAvailableCourse(String AC){
		this.availableCourse = AC;
	}
}
	