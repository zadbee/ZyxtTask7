package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import utility.PricePair;
import model.*;
import databeans.*;
import formbeans.*;

public class Emp_TransitionDayAction extends Action {
	FundDAO fundDAO;
	FundHistDAO histDAO;
	TransDAO transDAO;
	Emp_TransitionDayForm transForm;
	
	public Emp_TransitionDayAction(Model model) {
		fundDAO = model.getFundDAO();
		histDAO = model.getFundHistDAO();
		transDAO = model.getTransDAO();
	}
	
	@Override
	public String getName() {
		return "emp-transitionday.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		ArrayList<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);
		ArrayList<PricePair> prices = new ArrayList<PricePair>();
		request.setAttribute("prices", prices);
		
		// Get the updated price and update it.
		Fund[] funds = null;
		try {
			funds = fundDAO.match();
		} catch (RollbackException e) {
			e.printStackTrace();
		}
		
		if (funds == null || funds.length == 0) {
			errors.add("There is not any funds.");
			return "emp-transitionday.jsp";
		}
		
		// Initialize the fund information for displaying first.
		// Can also fresh the price if some other employee sets the new price.
		for (Fund f : funds)
			prices.add(new PricePair(f.getFund_id()));
		
		transForm = new Emp_TransitionDayForm(request);
		
		if (!transForm.isPresent())
			return "emp-transitionday.jsp";
		
		errors.addAll(transForm.getValidationErrors());
		if (errors.size() > 0)
			return "emp-transitionday.jsp";
		
		// Update all prices.
		histDAO.updateAll(transForm.prices);
		
		// Handle all pending transactions. 
		transDAO.clearPending();
		
		return null;
	}

}
