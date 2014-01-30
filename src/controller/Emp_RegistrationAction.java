package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Employee;
import formbeans.Emp_RegistrationForm;
import model.Model;

public class Emp_RegistrationAction extends Action {
	private FormBeanFactory<Emp_RegistrationForm> formBeanFactory = FormBeanFactory.getInstance(Emp_RegistrationForm.class);
	private GenericDAO<Employee> employeeDAO;
	
	public Emp_RegistrationAction(Model model){
		employeeDAO = model.getEmployeeDAO();
	}

	@Override
	public String getName() {
		return "emp-registration.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		ArrayList<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);
		
        try {
	    	Emp_RegistrationForm form = formBeanFactory.create(request);
			Employee employee = (Employee) request.getSession(false).getAttribute("employee");
	        if(employee==null){
	        	return "emp-login.jsp";
	        }

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        
	    	if (!form.isPresent()) {
	            return "emp-registration.jsp";
	        }
	        
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "emp-registration.jsp";
	        }
	        Employee newEmployee = new Employee();
	        newEmployee.setFirstname(form.getFirstName());
	        newEmployee.setLastname(form.getLastName());
	        newEmployee.setUsername(form.getUsername());
	        newEmployee.setPassword(form.getPassword());
	        
	        employeeDAO.create(newEmployee);

	        request.setAttribute("message","Employee "+ newEmployee.getUsername() + " is created.");
			return "emp-success.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	System.out.println(e.getMessage());
        	return "emp-registration.jsp";
        } catch (RollbackException e) {
        	errors.add("Username already exists");
        	System.out.println(e.getMessage());
        	request.setAttribute("errors", errors);
        	return "emp-registration.jsp";
        }
	}

}
