package beans;

import servlets.loginCheck;

public class LoginBean{
	
	String errorMessage = "";
	String id = "";
	
	public String getError(){
		return errorMessage;
	}
	
	public void setError(String message){
		this.errorMessage = message;
	}
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
}
