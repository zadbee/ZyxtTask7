package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import databeans.Customer;
import databeans.Position;
import model.CustomerDAO;
import model.FundDAO;
import model.FundHistDAO;
import model.Model;
import model.PosDAO;

public class Cus_ViewAccountAction extends Action {
	private CustomerDAO customerDAO;
	private FundDAO	fundDAO;
	private FundHistDAO fundHistDAO;
	private PosDAO positionDAO;
	
	public Cus_ViewAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		fundHistDAO = model.getFundHistDAO();
		positionDAO = model.getPosDAO();		
	}
	
	public String getName() {
		return "cus_viewAccount.do";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
		    Customer customer = (Customer) request.getSession(false).getAttribute("customer");          
            if(customer == null) {
                return "cus-login.jsp";
            }
            customer = customerDAO.readByName(customer.getUsername());
            Position[] pos =  positionDAO.readByCustomerID(customer.getCustomer_id());
            
            ArrayList<Integer> shares = new ArrayList<Integer>();
            ArrayList<String> funds = new ArrayList<String>();
            ArrayList<Long> prices = new ArrayList<Long>();
            
            for(Position x:pos){
            	shares.add(x.getShares());
            	funds.add(fundDAO.read(x.getFund_id()).getName());
            	prices.add(fundHistDAO.getPrice(x.getFund_id()).getPrice());
            }
            
			request.setAttribute("shares",shares);
			request.setAttribute("funds",funds);
			request.setAttribute("prices",prices);
			
	        return "cus_viewAccount.do";
	  } catch (Exception e) {
      	errors.add(e.toString());
      	return "error.jsp";
      }
	}
}
