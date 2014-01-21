package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;

import org.genericdao.GenericDAO;
import org.mybeans.form.FormBeanFactory;

import databeans.Employee;
import formbeans.Cus_ChangePwdForm;
import formbeans.Emp_ChangePwdForm;

public class Emp_ChangePwdAction extends Action{
	private FormBeanFactory<Emp_ChangePwdForm> formBeanFactory = FormBeanFactory
			.getInstance(Emp_ChangePwdForm.class);

	private GenericDAO<Employee> employeeDAO;

	public Emp_ChangePwdAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "emp_changePwd.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			Emp_ChangePwdForm form = formBeanFactory.create(request);
			Employee employee = (Employee) request.getSession().getAttribute("employee");

			//Employee employee = (Employee) request.getSession(false).getAttribute("employee");
            if(employee == null) {
                return "employee-login.do";
            }
           
			request.setAttribute("form", form);
			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "change-pwd-emp.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				System.out.println(errors.toString());
				return "change-pwd-emp.jsp";
			}
		
			// Change the password
			employee.setPassword(form.getNewPassword());
            employeeDAO.update(employee);
			//employeeDAO.setPassword(employee.getUsername(), form.getNewPassword());
			
			request.setAttribute("message","Password changed for "+employee.getUsername());
	        return "success.jsp";
	  } catch (Exception e) {
      	errors.add(e.toString());
      	//Unsure
      	return "login.jsp";
      }
	}
}
