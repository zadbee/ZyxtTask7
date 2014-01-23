package formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import databeans.FundPriceHistory;
import utility.AmountCheck;

public class Emp_TransitionDayForm {
	public ArrayList<FundPriceHistory> prices = null;
	private ArrayList<String> sprices = null;
	
	private String button = null;
	
	public boolean isPresent() { return button == null; }
	
	@SuppressWarnings("unchecked")
	public Emp_TransitionDayForm(HttpServletRequest request) {
		button = request.getParameter("transbutton");
		prices = (ArrayList<FundPriceHistory>) request.getAttribute("prices");
		sprices = new ArrayList<String>();
		for (FundPriceHistory p : prices)
			sprices.add(request.getParameter("price_" + p.getFund_id())); 
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		for (int i = 0; i < sprices.size(); i++) {
			String s = sprices.get(i);
			if (s == null || s.length() == 0)
				errors.add("The price of " + prices.get(i).getFund_id() + " is empty.");
			else {
				int np = AmountCheck.checkString(s);
				if (np < 0)
					errors.add(AmountCheck.getErrorByCode(sprices.get(i), np));
				prices.get(i).setPrice(np);
			}
		}	
		
		return errors;
	}
}
