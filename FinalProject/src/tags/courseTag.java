package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import dbUtilities.dbUtility;

public class courseTag extends SimpleTagSupport{
	
	public void doTag() throws JspException, IOException {
		dbUtility db = new dbUtility();
		String result = db.getCourseList();
	    JspWriter out = getJspContext().getOut();
	    out.println(result);
	   }
	
}
