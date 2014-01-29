package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import databeans.Customer;
import databeans.Fund;
import databeans.FundPriceHistory;
import model.FundDAO;
import model.FundHistDAO;
import model.Model;

public class Cus_GetFundDetailsAction extends Action{
	private FundHistDAO fundHistDAO;
	private FundDAO fundDAO;
	public Cus_GetFundDetailsAction(Model model) {
		fundHistDAO = model.getFundHistDAO();
		fundDAO = model.getFundDAO();
	}
	
	public String getName() {
		return "cus_getFundDetails.do";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {		
		    Customer customer = (Customer) request.getSession(false).getAttribute("customer");          
            if(customer == null)
                return "cus-login.jsp";
            
            if (errors.size() != 0)
				return "cus-get-fund-details.jsp";
            
			int fund_id = Integer.parseInt(request.getParameter("fund_id"));

			ArrayList<FundPriceHistory> histories = fundHistDAO.getFundHist(fund_id);
			request.setAttribute("histories", histories);
			
			Fund fund = fundDAO.read(fund_id);
			request.setAttribute("fund", fund);
			
			return "cus-get-fund-details.jsp";			
		}catch(Exception e){
			errors.add(e.toString());
			return "cus-get-fund-details.jsp";
		}
	}
}
