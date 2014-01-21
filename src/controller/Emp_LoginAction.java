/** 
 * Author: 			Bo ZHANG
 * Last Edit:		01/20/2014
 * Class Name:		Emp_LoginAction
 * Annotation:		NULL
 */

package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;

import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Employee;
import formbeans.Emp_LoginForm;

public class Emp_LoginAction extends Action {
	private FormBeanFactory<Emp_LoginForm> formBeanFactory = FormBeanFactory.getInstance(Emp_LoginForm.class);
	
	private GenericDAO<Employee> employeeDAO;
	
	public Emp_LoginAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}
	
	@Override
	public String getName() {
		return "emp-login.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		ArrayList<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);
		
        try {
	    	Emp_LoginForm form = formBeanFactory.create(request);
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
	        Employee emp = employeeDAO.read(form.getUsername());
	        if (emp == null) {
	            errors.add("User" + form.getUsername() + "not found");
	            return "login.jsp";
	        }

	        // Check the password
	        if (!emp.getPassword().equals(form.getPassword())) {
	            errors.add("Incorrect password");
	            return "login.jsp";
	        }
	
	        // Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession();
	        session.setAttribute("employee", emp);
	        session.setAttribute("identity", "employee");

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
