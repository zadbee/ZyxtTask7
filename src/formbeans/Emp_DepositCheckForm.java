package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

import utility.AmountCheck;

public class Emp_DepositCheckForm extends FormBean {
	private String userName;
	private String deposit;
	
	public String getUserName()  { return userName;  }
	public String getDeposit()   { return deposit;   }
	
	public void setUserName(String s)  { userName  = trimAndConvert(s,"<>\"");  }
	public void setDeposit(String s)   { deposit  = s.trim();                   }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0) {
			errors.add("Username is required");
		}

		if (deposit == null || deposit.length() == 0) {
			errors.add("Deposit amount is required");
		}

		long ec = AmountCheck.checkShareString(deposit);
		if (ec < 0)
			errors.add(AmountCheck.getErrorByCode(deposit, ec));
		
		return errors;
	}
}
