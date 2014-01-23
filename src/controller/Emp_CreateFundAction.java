package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Employee;
import databeans.Fund;
import formbeans.Emp_CreateFundForm;
import model.FundDAO;
import model.Model;


public class Emp_CreateFundAction extends Action {   
    private FormBeanFactory<Emp_CreateFundForm> formBeanFactory = FormBeanFactory.getInstance(Emp_CreateFundForm.class);
    private FundDAO fundDAO;
    
    public Emp_CreateFundAction(Model model) {
        fundDAO = model.getFundDAO();
    }
    
    public String getName() { return "create-fund-emp.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        System.out.println("Fund creating...");
        
        try {         
            Employee employee = (Employee) request.getSession(false).getAttribute("employee");
            if(employee == null) {
                return "employee-login.do";
            }
            
            Emp_CreateFundForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);
            
            System.out.println("Where...");
            if (!form.isPresent()) {
                return "create-fund-emp.jsp";
            }
            System.out.println("Have form...");

            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "create-fund-emp.jsp";
            }
            
            // Create new fund
            Fund fund = fundDAO.read(form.getFundName(), form.getFundSymbol());
            if (fund != null) {
                errors.add(fund.getName() + "["+fund.getSymbol()+"] already exists!");
                return "create-fund-emp.jsp";
            }
            
			// Attach (this copy of) the user bean to the session
            fund = new Fund();
            fund.setName(form.getFundName());
            fund.setSymbol(form.getFundSymbol());
            fundDAO.createAutoIncrement(fund);
            System.out.println("Fund created.");
			
            request.setAttribute("fund", fund);
            errors.add("Fund has been created");
            return "create-fund-emp.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
            return "error.jsp";
		}
    }
}
