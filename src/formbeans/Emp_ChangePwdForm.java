package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class Emp_ChangePwdForm extends FormBean{
	private String confirmPassword;
	private String newPassword;
	
	public String getConfirmPassword() { return confirmPassword; }
	public String getNewPassword()     { return newPassword;     }
	
	public void setConfirmPassword(String s) { confirmPassword = s.trim(); }
	public void setNewPassword(String s)     { newPassword     = s.trim(); }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (newPassword == null || newPassword.length() == 0) {
			errors.add("New Password is required");
		}
		
		if (confirmPassword == null || confirmPassword.length() == 0) {
			errors.add("Confirm Pwd is required");
		}
		
		if (!newPassword.matches("[0-9a-zA-Z]{1,12}$")) {
            errors.add("Password must be alphanumeric character of length 1~12");
        }
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!newPassword.equals(confirmPassword)) {
			errors.add("Passwords do not match");
		}

		return errors;
	}
}
