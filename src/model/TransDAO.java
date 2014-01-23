package model;

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
	
	
	
	public void clearPending() throws RollbackException {
		// Only handle pending BUYs and SELLs
		Transaction[] pending = match(MatchArg.and(MatchArg.equals("status", "PENDING"), 
				MatchArg.notEquals("transaction_type", "DEPOSIT"),
				MatchArg.notEquals("transaction_type", "WITHDRAW")));
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
				user.setCash(user.getCash() + sellAmount);
				customerDAO.update(user);
			} else {
				FundPriceHistory price = histDAO.getPrice(t.getFund_id());
				if (price == null)
					continue;
				
				// Update the position table.
				Position pos = posDAO.getShares(t.getCustomer_id(), t.getFund_id());
				if (pos == null)
					continue;
				long sellShares = Math.round(t.getAmount() / price.getPrice());
				pos.setShares(pos.getShares() + sellShares);
				posDAO.update(pos);
			}
			t.setStatus("APPROVED");
			update(t);
		}
	}
}
