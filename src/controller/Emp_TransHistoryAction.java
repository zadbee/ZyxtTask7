package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;

import databeans.Customer;
import databeans.Employee;
import databeans.Fund;
import databeans.Transaction;
import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.TransDAO;

public class Emp_TransHistoryAction extends Action{
	private TransDAO transactionDAO;
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	
    public Emp_TransHistoryAction(Model model) {
        transactionDAO = model.getTransDAO();
        customerDAO = model.getCustomerDAO();
        fundDAO = model.getFundDAO();
    }
    
	public String getName() {
		return "emp_transHistory.do";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try {
		    Employee employee = (Employee) request.getSession(false).getAttribute("employee"); 
		    Customer customer = null;
		    //Customer customer = (Customer) request.getSession(false).getAttribute("customer");   
		    if(employee==null){
		    	return "emp-login.jsp";
		    }
		    String button = request.getParameter("transhistory");
            //String thisButton = request.getParameter("button");

            if (button != null)
            	customer = customerDAO.read(Integer.parseInt(button));
            System.out.println(customer.getCustomer_id());

            
            Transaction[] trans = transactionDAO.match(MatchArg.equals("customer_id", customer.getCustomer_id()));

            Fund[] funds = new Fund[trans.length];
            
            for(int i=0; i<funds.length; i++){
            	funds[i] = fundDAO.read(trans[i].getFund_id());
            }

            request.setAttribute("customer", customer);
            request.setAttribute("trans", trans);
            request.setAttribute("funds", funds);
            
	        return "emp-cus-trans-history.jsp";
	  } catch (NullPointerException e) {
	      	return "emp-cus-trans-history.jsp";
	  } catch (Exception e) {
      	errors.add(e.toString());
      	return "emp-cus-trans-history.jsp";
	  } 
	}
}
