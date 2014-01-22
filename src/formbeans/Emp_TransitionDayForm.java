package formbeans;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import utility.AmountCheck;
import utility.PricePair;

public class Emp_TransitionDayForm {
	public ArrayList<PricePair> prices = null;
	private ArrayList<String> sprices = null;
	
	private String button = null;
	
	public boolean isPresent() { return button == null; }
	
	@SuppressWarnings("unchecked")
	public Emp_TransitionDayForm(HttpServletRequest request) {
		button = request.getParameter("transbutton");
		prices = (ArrayList<PricePair>) request.getAttribute("prices");
		sprices = new ArrayList<String>();
		for (PricePair p : prices)
			sprices.add(request.getParameter("price_" + p.getId())); 
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		for (int i = 0; i < prices.size(); i++) {
			int np = AmountCheck.checkString(sprices.get(i));
			if (np < 0)
				errors.add(AmountCheck.getErrorByCode(sprices.get(i), np));
			prices.get(i).setPrice(np);
		}	
		
		return errors;
	}
}
