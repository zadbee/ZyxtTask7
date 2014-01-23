package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

import utility.AmountCheck;

public class Cus_SellFundForm extends FormBean{
	private String fundName;
	private String shares;
	private String fundId;
	
	
	public void setShares(String shares) {
		this.shares = shares;
	}


	public String getFundName() {
		return fundName;
	}

	public String getFundId() {
	    return fundId;
	}
	
	public void setFundId(String id) {
	    this.fundId = id;
	}
	
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getShares() {
		return shares;
	}


	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (fundId == null || fundId.length() == 0) {
			errors.add("Fund is required.");
		}
		
		if (shares == null || shares.length() == 0) {
			errors.add("Amount is required");
		}
		
		long ec = AmountCheck.checkShareString(shares);
		if (ec < 0)
			errors.add(AmountCheck.getErrorByCode(shares, ec));
		
		return errors;
	}


	

	
}
