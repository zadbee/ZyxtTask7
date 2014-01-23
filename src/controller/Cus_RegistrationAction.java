package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import formbeans.Cus_RegistrationForm;
import model.CustomerDAO;
import model.Model;

public class Cus_RegistrationAction extends Action {
	private FormBeanFactory<Cus_RegistrationForm> formBeanFactory = FormBeanFactory.getInstance(Cus_RegistrationForm.class);
	private CustomerDAO customerDAO;
	
	public Cus_RegistrationAction(Model model) {
		System.out.println("----------------------- here1");
		customerDAO = model.getCustomerDAO();
	}
	
	@Override
	public String getName() {
		System.out.println("----------------------- here2");
		// TODO Auto-generated method stub
		return "cus-registration.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("----------------------- here3");
		ArrayList<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);
		
        try {
	    	Cus_RegistrationForm form = formBeanFactory.create(request);
	        // request.setAttribute("form",form);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "cus-registration.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "cus-registration.jsp";
	        }
	        
	        Customer customer = new Customer();
	        customer.setFirstname(form.getFirstName());
	        customer.setLastname(form.getLastName());
	        customer.setUsername(form.getUsername());
	        customer.setPassword(form.getPassword());
	        customer.setAddr_line1(form.getAddrline1());
	        customer.setAddr_line2(form.getAddrline2());
	        customer.setCity(form.getCity());
	        customer.setState(form.getState());
	        customer.setCash(form.getCash());
	        
	        customerDAO.createAutoIncrement(customer);
	        return "emp-create-fund.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
	}

}
