package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import utility.AmountCheck;
import databeans.Customer;
import databeans.Employee;
import databeans.Transaction;
import formbeans.Emp_DepositCheckForm;
import model.Model;
import model.CustomerDAO;
import model.TransDAO;



public class Emp_DepositCheckAction extends Action {   
    private FormBeanFactory<Emp_DepositCheckForm> formBeanFactory = FormBeanFactory.getInstance(Emp_DepositCheckForm.class);
    private CustomerDAO customerDAO;
    private TransDAO transDAO;
    
    public Emp_DepositCheckAction(Model model) {
        customerDAO = model.getCustomerDAO();
        transDAO = model.getTransDAO();
    }
    
    public String getName() { return "emp_depositCheck.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        Long amount = 0l;
        
        try {
        	Customer customer = null;
        	Emp_DepositCheckForm form = formBeanFactory.create(request);
		    Employee employee = (Employee) request.getSession(false).getAttribute("employee");
            if(employee == null) {
                return "emp-login.do";
            }
            String button = request.getParameter("deposit-check");
            String thisButton = request.getParameter("button");

            if (thisButton != null){

            	customer = customerDAO.read(Integer.parseInt(thisButton));
            	amount = AmountCheck.checkValueString(form.getDeposit());
            	if (amount < 0) {
            		errors.add(AmountCheck.getErrorByCode(form.getDeposit(), amount));
            		return "emp-deposit-check.jsp";
            	}
   			 
                Transaction transaction = new Transaction();
                transaction.setAmount(amount);
                transaction.setCustomer_id(customer.getCustomer_id());
                transaction.setExecute_date(null);
                transaction.setTransaction_type("DEPOSIT");
                transaction.setStatus("PENDING");
                transDAO.createAutoIncrement(transaction);

                request.getSession().setAttribute("customer", customer);
            	request.setAttribute("cash", customer.getCash());
                
                System.out.println("The employee =>"+employee.getUsername()+" just deposited a check of =>$"+amount/100+" for customer =>"+customer.getUsername()+"\n");
            	
                request.setAttribute("message","$" + transaction.getAmount() / 100.0 +" deposit requested for " + customer.getFirstname() + ".");
    			return "emp-success.jsp";
            }
            
            if (button != null){
            	customer = customerDAO.read(Integer.parseInt(button));
            	request.setAttribute("customer", customer);
            	return "emp-deposit-check.jsp";
            }
            request.setAttribute("form",form);

            
            if (!form.isPresent()) {
                return "emp-deposit-check.jsp";
            }
            
            // Any validation errors?
            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
                return "emp-deposit-check.jsp";
            }
            
        } catch (Exception e) {
            errors.add(e.getMessage());
            e.printStackTrace();
            return "error.jsp";
        }
		return null; 
    }
}
