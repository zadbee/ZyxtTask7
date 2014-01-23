package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Customer;
import databeans.FundPriceHistory;
import databeans.Transaction;

public class TransDAO extends GenericDAO<Transaction> {
	CustomerDAO customerDAO;
	FundHistDAO histDAO;
	public TransDAO(ConnectionPool pool, String tableName, CustomerDAO cdao, FundHistDAO hdao) throws DAOException {
		super(Transaction.class, tableName, pool);
	}
	
	public void clearPending() throws RollbackException {
		// Only handle pending BUYs and SELLs
		Transaction[] pending = match(MatchArg.and(MatchArg.equals("status", "PENDING"), 
				MatchArg.notEquals("transaction_type", "DEPOSIT"),
				MatchArg.notEquals("transaction_type", "WITHDRAW")));
		System.out.println("Handling all pending transactions.");
		for (Transaction t : pending) {
			if (t.getTransaction_type().equals("SELL")) {
				System.out.println("Handling sale of " + t.getFund_id());
				Customer user = customerDAO.read(t.getCustomer_id());
				if (user == null)
					continue;
				FundPriceHistory price = histDAO.getPrice(t.getFund_id());
				if (price == null)
					continue;
				System.out.println(price.getPrice() + ", " + t.getShares());
				long sellAmount = Math.round(price.getPrice() * (t.getShares() / 1000.0));
				System.out.println("Cash increased by " + sellAmount);
				user.setCash(user.getCash() + sellAmount);
				customerDAO.update(user);
			}
			t.setStatus("APPROVED");
			update(t);
		}
	}
}
