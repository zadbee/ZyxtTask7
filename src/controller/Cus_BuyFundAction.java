package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.FundDAO;
import model.FundHistDAO;
import model.Model;
import model.TransDAO;

import org.mybeans.form.FormBeanFactory;

import utility.AmountCheck;
import databeans.Customer;
import databeans.Fund;
import databeans.FundPriceHistory;
import databeans.Transaction;
import formbeans.Cus_BuyFundForm;

public class Cus_BuyFundAction extends Action {
	private FormBeanFactory<Cus_BuyFundForm> formBeanFactory = FormBeanFactory.getInstance(Cus_BuyFundForm.class);
	
	private TransDAO transactionDAO;
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private FundHistDAO fundHistDAO;
	
	public Cus_BuyFundAction(Model model) {
		transactionDAO = model.getTransDAO();
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		fundHistDAO = model.getFundHistDAO();
	}
	
	public String getName() {
		return "cus_buyFund.do";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
		    Customer customer = (Customer) request.getSession(false).getAttribute("customer");          
            if(customer == null)
                return "cus-login.jsp";
            
            customer = customerDAO.read(customer.getCustomer_id());
            if(customer == null)
                return "cus-login.jsp";
            //request.getSession(false).setAttribute("customer", customer);
		    Cus_BuyFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			int customer_id = customer.getCustomer_id();
			long available = customer.getCash();
			
			ArrayList<Fund> allFunds = fundDAO.getAll();
			request.getSession().setAttribute("allFunds",allFunds);
			
			ArrayList<Long> allFundPrices = new ArrayList<Long>();
			for(Fund x : allFunds){ 
				if(fundHistDAO.getPrice(x.getFund_id())!=null)
					allFundPrices.add(fundHistDAO.getPrice(x.getFund_id()).getPrice());
				else
					allFundPrices.add(-1L);
			}
			request.getSession().setAttribute("allFundPrices",allFundPrices);		
			
			// If no params were passed, return with no errors so that the form
			// will be presented (we assume for the first time).
			if (!form.isPresent()) {
				return "cus-buy-fund.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0)
				return "cus-buy-fund.jsp";				

			long amount = AmountCheck.checkValueString(form.getAmount());
            if(amount > available){
            	errors.add("You don't have enough money!");
            	return "cus-buy-fund.jsp";
            }
      		
            String symbol = form.getFundSymbol();
            Fund fund = fundDAO.readBySymbol(symbol);
			Transaction t = new Transaction();
			
			t.setCustomer_id(customer_id);
			t.setFund_id(fund.getFund_id());
			t.setExecute_date(new Date());
			t.setTransaction_type("BUY");
			t.setAmount(amount);
			transactionDAO.createAutoIncrement(t);
				
			customer.setCash(available - amount);
			customerDAO.update(customer);
			request.getSession().setAttribute("customer",customer);
			
			request.setAttribute("message", 
					"You have successfully bought $" + (amount / 100.0) + " of fund " + fund.getFund_id() + ".");
	        return "cus-success.jsp";
	  } catch (Exception e) {
      	errors.add(e.toString());
      	return "cus-buy-fund.jsp";
      }
	}
}
