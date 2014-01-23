package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.FundDAO;
import model.FundHistDAO;
import model.Model;
import model.PosDAO;
import model.TransDAO;

import org.mybeans.form.FormBeanFactory;

import utility.AmountCheck;
import databeans.Customer;
import databeans.Fund;
import databeans.Position;
import databeans.Transaction;
import formbeans.Cus_SellFundForm;

public class Cus_SellFundAction extends Action{
	private FormBeanFactory<Cus_SellFundForm> formBeanFactory = FormBeanFactory
			.getInstance(Cus_SellFundForm.class);
	
	private TransDAO transactionDAO;
	private PosDAO positionDAO;
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private FundHistDAO historyDAO;
	public Cus_SellFundAction(Model model) {
		transactionDAO = model.getTransDAO();
		positionDAO = model.getPosDAO();
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		historyDAO = model.getFundHistDAO();
	}
	public String getName() {
		return "cus_sellFund.do";
	}
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
		    Customer customer = (Customer) request.getSession(false).getAttribute("customer");            
            if(customer == null) {
                return "cus-login.jsp";
            }
            
            Cus_SellFundForm form = formBeanFactory.create(request);
            
            errors.addAll(form.getValidationErrors());
            if (errors.size() > 0)
            	return "cus-sell-fund.jsp";
            
            int fundId = Integer.parseInt(form.getFundId());
            long shares = AmountCheck.checkShareString(form.getShares());
            
            Customer updatedCus = customerDAO.readByName(customer.getUsername());
            if (updatedCus == null)
            	return "cus-login.jsp";
            
            Position pos = positionDAO.getShares(customer.getCustomer_id(), fundId);            
            if (pos == null || shares > pos.getShares()) {
            	errors.add("You do not have enough share of fund " + fundId);
            	return "cus-sell-fund.jsp";
            }
            
            // Position table is updated.
            pos.setShares(pos.getShares() - shares);
            positionDAO.update(pos);     
            
            // Transaction table is updated.
            Transaction trans = new Transaction();
            trans.setCustomer_id(updatedCus.getCustomer_id());
            trans.setExecute_date(new Date());
            trans.setFund_id(fundId);
            trans.setShares(shares);
            trans.setStatus("PENDING");
            trans.setTransaction_type("SELL");
            transactionDAO.createAutoIncrement(trans);

            request.setAttribute("message", 
					"You have successfully sold " + (shares / 100.0) + "shares of fund " + fundId + ".");
	        return "cus-success.jsp";
	  } catch (Exception e) {
      	errors.add(e.toString());
      	return "cus_sellFund.do";
      }
	}

	
	
}
