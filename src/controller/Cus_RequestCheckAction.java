package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import utility.AmountCheck;
import databeans.Customer;
import databeans.Transaction;
import formbeans.Cus_RequestCheckForm;
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
    
    public String getName() { return "cus_requestCheck.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
            Cus_RequestCheckForm form = formBeanFactory.create(request);
            request.setAttribute("form",form);

            Customer customer = (Customer) request.getSession().getAttribute("customer");
            if (customer == null)
            	return "cus-login.jsp";
            
            request.setAttribute("cash", customer.getCash());

            if (!form.isPresent()) {
            	return "cus-request-check.jsp";
			}
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "cus-request-check.jsp";
            }
            
            long withdrawAmount = AmountCheck.checkValueString(form.getWithdraw());

            if (withdrawAmount > customer.getCash()) {
                errors.add("Withdraw amount cannot be greater than your current balance!");
                return "cus-request-check.jsp";
            }
            
			customerDAO.setCash(customer.getCustomer_id(),customer.getCash()-withdrawAmount);
	        customer = customerDAO.readByName(customer.getUsername());
			request.getSession().setAttribute("customer", customer);
			             
            
            // Any validation errors?
           
            Transaction transaction = new Transaction();
            transaction.setAmount(withdrawAmount);
            transaction.setCustomer_id(customer.getCustomer_id());
            transaction.setExecute_date(null);
            transaction.setTransaction_type("WITHDRAW"); 
            transaction.setStatus("PENDING");

            transDAO.createAutoIncrement(transaction);
      
            DecimalFormat nf = new DecimalFormat("#,##0.00");
            nf.setMaximumFractionDigits(2);
           	nf.setMinimumFractionDigits(2);
       
            	
            
            request.setAttribute("message","$" + nf.format(transaction.getAmount() / 100.0) +" check requested for "+customer.getFirstname()+". Current available cash is "+nf.format(customer.getCash()/100.0)+".");
			return "cus-success.jsp";
            
        } catch (Exception e) {
            errors.add(e.getMessage());
            return "error-list.jsp";
        }
    }
}