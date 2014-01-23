package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.TransDAO;

import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Fund;
import databeans.Transaction;
import formbeans.Cus_BuyFundForm;

public class Cus_BuyFundAction extends Action {
	private FormBeanFactory<Cus_BuyFundForm> formBeanFactory = FormBeanFactory.getInstance(Cus_BuyFundForm.class);
	
	private TransDAO transactionDAO;
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	public Cus_BuyFundAction(Model model) {
		transactionDAO = model.getTransDAO();
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
	}
	
	public String getName() {
		return "cus_buyFund.do";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
		    Customer customer = (Customer) request.getSession(false).getAttribute("customer");          
            if(customer == null) {
                return "cus-login.jsp";
            }
            
            customer = customerDAO.read(customer.getCustomer_id());
            //request.getSession(false).setAttribute("customer", customer);
		    Cus_BuyFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			int customer_id = customer.getCustomer_id();
			long available = customer.getCash();
			// If no params were passed, return with no errors so that the form
			// will be presented (we assume for the first time).
			if (!form.isPresent()) {
				return "cus-buy-fund.jsp";
			}
			
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "cus-buy-fund.jsp";
				
			}

			long amount = Long.parseLong(form.getAmount());
            if(amount > available){
            	errors.add("You don't have enough money!");
            	return "cus-buy-fund.jsp";
            }
            
			
            String symbol = form.getFundSymbol();
            Fund fund = fundDAO.readBySymbol(symbol);
            System.out.println("4");
			Transaction t = new Transaction();
			
			t.setCustomer_id(customer_id);
			t.setFund_id(fund.getFund_id());
			t.setExecute_date(null);
			t.setTransaction_type("BUY");
			t.setStatus("PENDING");
			t.setAmount(amount);
			System.out.println("4");
			transactionDAO.create(t);
			
			System.out.println("6");
			
			customer.setCash(available-amount);
			customerDAO.update(customer);
			request.setAttribute("customer",customer);
			
			
	        return "cus_buyFund.do";
	  } catch (Exception e) {
      	errors.add(e.toString());
      	return "error.jsp";
      }
	}
}
