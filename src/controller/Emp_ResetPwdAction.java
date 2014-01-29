package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.Model;

import org.genericdao.MatchArg;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Employee;
import formbeans.Emp_ResetPwdForm;

public class Emp_ResetPwdAction extends Action{
	private FormBeanFactory<Emp_ResetPwdForm> formBeanFactory = FormBeanFactory
			.getInstance(Emp_ResetPwdForm.class);

	private CustomerDAO customerDAO;

	public Emp_ResetPwdAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "emp_resetPwd.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			Customer customer = null;
			Emp_ResetPwdForm form = formBeanFactory.create(request);
		    Employee employee = (Employee) request.getSession(false).getAttribute("employee");
		    
            if (errors.size() > 0)
            	return "emp-reset-customer-pwd.jsp";
            if(employee == null) {
                return "emp-login.do";
            }
            String button = request.getParameter("reset-pwd");
            String thisButton = request.getParameter("button");
            if (thisButton != null){
            	Transaction.begin();
            	customer = customerDAO.read(Integer.parseInt(thisButton));
            	customer.setPassword(form.getPassword());
            	customerDAO.update(customer);
            	Transaction.commit();
            	request.setAttribute("message","Password changed for "+customer.getFirstname());
    			return "emp-pwdreset-success.jsp";
            }
            if (button != null)
            	customer = customerDAO.read(Integer.parseInt(button));

			request.setAttribute("form", form);
			String newPwd = null;
			String username;
			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				request.setAttribute("customer", customer);
				request.setAttribute("employee", employee);
				return "emp-reset-customer-pwd.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				System.out.println(errors.toString());
				return "emp-reset-customer-pwd.jsp";
			}
			
			username = form.getPassword();
			if(username == null){
				errors.add("No such users!");
			}
			// Change the password
			Random random = new Random();
			newPwd = String.valueOf(random.nextInt(1024)+1);
			customerDAO.setPassword(username, newPwd);
			if(newPwd == null || newPwd.length() == 0)
				errors.add("No such user!");
			else
				errors.add("The password has been reset to " + newPwd);
			request.setAttribute("customer", customer);
			request.setAttribute("employee", employee);
	        return "emp-reset-customer-pwd.jsp";
	  } catch (Exception e) {
      	errors.add(e.toString());
      	e.printStackTrace();
      	return "emp-reset-customer-pwd.jsp";
      }
	}
}
