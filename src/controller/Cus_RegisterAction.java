package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class Cus_RegisterAction extends Action {
	
	public Cus_RegisterAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "cust_register.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
