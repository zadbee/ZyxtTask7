package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Transaction;
import formbeans.Cus_RequestCheckForm;
import formbeans.Emp_DepositCheckForm;
import model.Model;
import model.TransDAO;
import model.CustomerDAO;


public class Cus_RequestCheckAction extends Action {   
    private FormBeanFactory<Cus_RequestCheckForm> formBeanFactory = FormBeanFactory.getInstance(Cus_RequestCheckForm.class);
    private CustomerDAO customerDAO;
    private TransDAO transDAO;
    
    public Cus_RequestCheckAction(Model model) {
        customerDAO = model.getCustomerDAO();
        transDAO = model.getTransDAO();
    }
    
    public String getName() { return "requestcheck.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
            Cus_RequestCheckForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);
            Customer customer = (Customer) request.getSession().getAttribute("customer");
            request.setAttribute("cash", customer.getCash());
            if (!form.isPresent()) {
            	return "cus-request-check.jsp";
			}
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "cus-request-check.jsp";
            }
            if(customer == null) {
                return "login-cus.jsp";
            }

            //customer = customerDAO.lookup(customer.getCustomer_id());
            //request.getSession(false).setAttribute("customer", customer);
            
            
            long withdrawAmount = Long.parseLong(form.getWithdraw());


            if (withdrawAmount > customer.getCash()) {
                errors.add("Withdraw amount cannot be greater than your current balance!");
                return "cus-request-check.jsp";
            }
            
			customerDAO.setCash(customer.getCustomer_id(),customer.getCash()-withdrawAmount);
	        customer = customerDAO.read(customer.getCustomer_id());

			request.getSession().setAttribute("customer", customer);
			 
			//double balance = customerDAO.getCash(customer.getCustomer_id());

            
            
            // Any validation errors?

           
            Transaction transaction = new Transaction();
            transaction.setAmount(withdrawAmount);
            transaction.setCustomer_id(customer.getCustomer_id());

            transaction.setExecute_date(new Date());
            transaction.setTransaction_type("WITHDRAW");   

            transDAO.createAutoIncrement(transaction);
       		//session.setAttribute("user", user);
            /*if(!transactionDAO.createForRequestCheck(transaction)) {
                errors.add("You don't have enough cash");
                customer = customerDAO.read(customer.getCustomer_id());
                request.getSession().setAttribute("customer", customer);
                return "request-check-cus.jsp";
            }*/

            	
            
            request.setAttribute("message","Check Requested for "+customer.getFirstname()+". Current cash is "+(customer.getCash()+"."));
			return "success.jsp";
            
        } catch (Exception e) {
            errors.add(e.getMessage());
            return "error-list.jsp";
        }
    }
}