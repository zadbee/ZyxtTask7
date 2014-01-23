package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Employee;
import formbeans.Cus_RegistrationForm;
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
        	System.out.println("************* 1");
	    	Emp_RegistrationForm form = formBeanFactory.create(request);
	        // request.setAttribute("form",form);

	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "emp-registration.do";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	        	System.out.println("************************ error");
	            return "emp-registration.do";
	        }
	        
	        System.out.println("******************* after validation");
	        Employee employee = new Employee();
	        employee.setFirstname(form.getFirstName());
	        employee.setLastname(form.getLastName());
	        employee.setUsername(form.getUsername());
	        employee.setPassword(form.getPassword());
	        
	        employeeDAO.create(employee);
	        System.out.println("********************* before return");
	        return "create-fund-emp.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	System.out.println(e.getMessage());
        	return "create-fund-emp.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	System.out.println(e.getMessage());
        	return "create-fund-emp.jsp";
        }
	}

}
