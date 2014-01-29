package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
<<<<<<< HEAD
=======


import org.genericdao.MatchArg;
>>>>>>> 11ba1a81d8975183b4733cccc897424f017812bf

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
//        	System.out.println("********here");
//        	String button = request.getParameter("manage_cust_1");
//        	System.out.println("^^^^^^^^^^"+button);
//        	if ("manage".equals(request.getParameter("manage_cust_1")))
//        		System.out.println(";;;;;;;;;;;;;;;;;;; Alo ki ithe");	
        	Customer[] customers = customerDAO.match();
        	String button = null;
        	int button_id = -1;
        	for (Customer customer : customers) {
				button = request.getParameter("reset_custPwd_"+customer.getCustomer_id());
				if (button != null){
					request.setAttribute("customer", customer);
					System.out.println("Here---------------------- 0");
					return "emp_resetPwd.do";
				}
			}
        	
        	for (Customer customer : customers) {
				button = request.getParameter("manage_cust_"+customer.getCustomer_id());
				if (button != null){
					button_id = customer.getCustomer_id();
					break;
				}
			}
        	System.out.println("The button that was clicked was "+ button_id);
        	Employee employee = (Employee) request.getSession(false).getAttribute("employee");
        	if (employee == null) {
        		System.out.println("*********login kar ki");
				return "emp-login.do";
			}
        	
//        	Customer[] customers = customerDAO.match();
        	for (Customer customer : customers) {
				System.out.println("---"+customer);
			}
        	System.out.println("***********"+employee.toString());
        	if (button_id == -1){
	        	request.setAttribute("customers", customers);
	        	return "emp-customerlist.jsp";
        	} else {
        		customers = customerDAO.match(MatchArg.equals("customer_id", button_id));
        		System.out.println(customers[0]);
        		request.setAttribute("customer", customers[0]);
        		return "emp-manage-customer.jsp";
        	}
        }catch (Exception e){
        	
        }
		return null;
	}

}
