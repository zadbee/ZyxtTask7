package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.GenericDAO;

import databeans.Employee;
import model.Model;

public class Emp_LogoutAction extends Action {
	private GenericDAO<Employee> employeeDAO;
	
    public Emp_LogoutAction(Model model) { employeeDAO = model.getEmployeeDAO(); }
	@Override
	public String getName() {
		return "emp-logout.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		ArrayList<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);
		
        HttpSession session = request.getSession(false);
        session.setAttribute("employee", null);
        session.setAttribute("identity", null);

		request.setAttribute("message","You are now logged out!");
        return "success.jsp";
    }
}
