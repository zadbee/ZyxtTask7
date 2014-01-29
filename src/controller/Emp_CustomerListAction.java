package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


import databeans.Customer;
import databeans.Employee;
import model.CustomerDAO;
import model.Model;

public class Emp_CustomerListAction extends Action {
	
	private CustomerDAO customerDAO;
	
	public Emp_CustomerListAction(Model model){
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "emp-customerlist.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		ArrayList<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);

        try {
        	System.out.println("********here");
        	String button = request.getParameter("manage_cust_1");
        	System.out.println("^^^^^^^^^^"+button);
        	if ("manage".equals(request.getParameter("manage_cust_1")))
        		System.out.println(";;;;;;;;;;;;;;;;;;; Alo ki ithe");		
        	Employee employee = (Employee) request.getSession(false).getAttribute("employee");
        	if (employee == null) {
        		System.out.println("*********login kar ki");
				return "emp-login.do";
			}
        	
        	Customer[] customers = customerDAO.match();
        	for (Customer customer : customers) {
				System.out.println("---"+customer);
			}
        	System.out.println("***********"+employee.toString());
        	
        	request.setAttribute("customers", customers);
        	return "emp-customerlist.jsp";
        }catch (Exception e){
        	
        }
		return null;
	}

}
