package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class Emp_ResetPwdForm extends FormBean{
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = trimAndConvert(userName,"<>\";*");
	}
	public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        if(userName == null || userName.length() == 0){
        	errors.add("No such user!");
        }
        if (userName.matches(".*[<>\"].*")) errors.add("You may not input angle brackets, quotes, semicolons or stars in textfields");
        return errors;
    }
}
