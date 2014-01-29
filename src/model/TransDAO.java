package model;

import java.util.Arrays;
import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Customer;
import databeans.FundPriceHistory;
import databeans.Position;
import databeans.Transaction;

public class TransDAO extends GenericDAO<Transaction> {
	CustomerDAO customerDAO;
	FundHistDAO histDAO;
	PosDAO posDAO;
	public TransDAO(ConnectionPool pool, String tableName, CustomerDAO cdao, FundHistDAO hdao, PosDAO pdao) throws DAOException {
		super(Transaction.class, tableName, pool);
		customerDAO = cdao;
		histDAO = hdao;
		posDAO = pdao;
	}
	
	public Date lastTradingDay(int cus_id) throws RollbackException{ 	
		Transaction[] trans = match(MatchArg.equals("customer_id", cus_id));
		Date[] dates ;
		if(trans!=null && trans.length!=0){
			dates = new Date[trans.length];
			for(int i=0; i<trans.length;i++){
				dates[i] = trans[i].getExecute_date();
			}
			Arrays.sort(dates);
			return dates[trans.length-1];
		}
		return null;
	}
	
	public void clearPending(Date date) throws RollbackException {
		// Only handle pending BUYs and SELLs
		Transaction[] pending = match(MatchArg.equals("status", "PENDING"));
		System.out.println("Handling all pending transactions.");
		for (Transaction t : pending) {
			if (t.getTransaction_type().equals("SELL")) {
				Customer user = customerDAO.read(t.getCustomer_id());
				if (user == null)
					continue;
				FundPriceHistory price = histDAO.getPrice(t.getFund_id());
				if (price == null)
					continue;
				
				// Update the cash amount.
				long sellAmount = Math.round(price.getPrice() * (t.getShares() / 1000.0));
				t.setAmount(sellAmount);
				user.setCash(user.getCash() + sellAmount);
				customerDAO.update(user);
			} else if (t.getTransaction_type().equals("BUY")){
				FundPriceHistory price = histDAO.getPrice(t.getFund_id());
				if (price == null)
					continue;
				
				// Update the position table.
				Position pos = posDAO.getShares(t.getCustomer_id(), t.getFund_id());
				long buyShares = Math.round(t.getAmount() * 1000.0 / price.getPrice());
				if (pos == null) {
					pos = new Position();
					pos.setCustomer_id(t.getCustomer_id());
					pos.setFund_id(t.getFund_id());
					pos.setShares(buyShares);
					posDAO.createAutoIncrement(pos);
				}
				else {
					pos.setShares(pos.getShares() + buyShares);
					posDAO.update(pos);
				}
				t.setShares(buyShares);		
			}
			t.setExecute_date(date);
			t.setStatus("APPROVED");
			update(t);
		}
	}
}
