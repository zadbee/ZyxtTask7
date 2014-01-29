package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Employee;
import databeans.Transaction;
import formbeans.Emp_DepositCheckForm;
import model.Model;
import model.CustomerDAO;



public class Emp_DepositCheckAction extends Action {   
    private FormBeanFactory<Emp_DepositCheckForm> formBeanFactory = FormBeanFactory.getInstance(Emp_DepositCheckForm.class);
    private CustomerDAO customerDAO;
    
    public Emp_DepositCheckAction(Model model) {
        customerDAO = model.getCustomerDAO();
    }
    
    public String getName() { return "emp_depositCheck.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
        	System.out.println("--------------------- In deposit check action");
            Employee employee = (Employee) request.getSession(false).getAttribute("employee");
            if(employee == null) {
                return "emp-login.do";
            }
            
            Emp_DepositCheckForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);
            Customer customer = (Customer) request.getSession().getAttribute("customer");

            
            if (!form.isPresent()) {
                return "emp-deposit-check.jsp";
            }
            
            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "emp-deposit-check.jsp";
            }
            
            // Look up the customer
//            Customer customer = customerDAO.lookup(form.getUserName());
            Long amount = Long.parseLong(form.getDeposit());
            //customer.setCash(customer.getCash() + amount);
            
            //customerDAO.updateCash(customer);
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setCustomer_id(customer.getCustomer_id());
            transaction.setExecute_date(new Date());
            transaction.setTransaction_type("DEPOSIT");
            transaction.setStatus("PENDING");
            
            // Attach (this copy of) the customer object to the session
            customerDAO.setCash(customer.getCustomer_id(),customer.getCash()-amount);
			 
			//double balance = customerDAO.getCash(customer.getCustomer_id());
            request.setAttribute("cash", customer.getCash());
            
            
            request.setAttribute("message","Deposit Requested for "+customer.getFirstname() + "Current cash is" + customer.getCash() );
			return "success.jsp";
        } catch (Exception e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } 
    }
}
