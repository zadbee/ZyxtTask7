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
			System.out.println("------------hello-----------------");
		    Employee employee = (Employee) request.getSession(false).getAttribute("employee");
		    System.out.println("^^^^^^^^^");
            if(employee == null) {
                return "emp-login.do";
            }
            System.out.println("^^^^^^^^^");
            String button = request.getParameter("deposit-check");
            String thisButton = request.getParameter("button");
            System.out.println("the string is "+thisButton);
            if (thisButton != null){
            	System.out.println("------- in this button loop");
            	//org.genericdao.Transaction.begin();
            	//customerDAO.updateCash(customer);
            	customer = customerDAO.read(Integer.parseInt(thisButton));
            	amount = Long.parseLong(form.getDeposit());
            	
            	
   			 
                Transaction transaction = new Transaction();
                transaction.setAmount(amount);
                transaction.setCustomer_id(customer.getCustomer_id());
                transaction.setExecute_date(null);
                transaction.setTransaction_type("DEPOSIT");
                transaction.setStatus("PENDING");
                transDAO.createAutoIncrement(transaction);
                //org.genericdao.Transaction.commit();
                // Attach (this copy of) the customer object to the session
                customerDAO.setCash(customer.getCustomer_id(),customer.getCash()+amount);
                customer = customerDAO.read(Integer.parseInt(thisButton));
                request.getSession().setAttribute("customer", customer);
            	request.setAttribute("cash", customer.getCash());
                
                
                request.setAttribute("message","Deposit Requested for "+customer.getFirstname() + "Current cash is" + customer.getCash() );
    			return "emp-success.jsp";
            }
            
            if (button != null){
            	System.out.println("------------ in button loop");
            	customer = customerDAO.read(Integer.parseInt(button));
            	request.setAttribute("customer", customer);
            	System.out.println(customer.toString());
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
            
            // Look up the customer
//            Customer customer = customerDAO.lookup(form.getUserName());
            
            //customer.setCash(customer.getCash() + amount);
            
            //customerDAO.updateCash(customer);
             
			//double balance = customerDAO.getCash(customer.getCustomer_id());
            
        } catch (Exception e) {
            errors.add(e.getMessage());
            e.printStackTrace();
            return "error.jsp";
        }
		return null; 
    }
}
