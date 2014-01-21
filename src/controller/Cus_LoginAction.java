/** 
 * Author: 			Bo ZHANG
 * Last Edit:		01/20/2014
 * Class Name:		Cus_LoginAction
 * Annotation:		NULL
 */

package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import formbeans.*;
import model.*;

public class Cus_LoginAction extends Action {
	private FormBeanFactory<Cus_LoginForm> formBeanFactory = FormBeanFactory.getInstance(Cus_LoginForm.class);
	
	private CustomerDAO customerDAO;
	
	public Cus_LoginAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}
	
	@Override
	public String getName() {
		return "cus-login.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		ArrayList<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);
		
        try {
	    	Cus_LoginForm form = formBeanFactory.create(request);
	        // request.setAttribute("form",form);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "login.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "login.jsp";
	        }

	        // Look up the user
	        Customer cus = customerDAO.read(form.getUsername());
	        if (cus == null) {
	            errors.add("User" + form.getUsername() + "not found");
	            return "login.jsp";
	        }

	        // Check the password
	        if (!cus.getPassword().equals(form.getPassword())) {
	            errors.add("Incorrect password");
	            return "login.jsp";
	        }
	
	        // Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession();
	        session.setAttribute("customer", cus);
	        session.setAttribute("identity", "customer");

	        return "manage.do";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
		}
	}
}
