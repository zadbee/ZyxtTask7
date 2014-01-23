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
<<<<<<< HEAD

		

=======
>>>>>>> ef736dc7309c1d9e5ec0ba7830b28962e66e67c0
		customerDAO = cdao;
		histDAO = hdao;
	}
	
	public void clearPending() throws RollbackException {
		// Only handle pending BUYs and SELLs
		Transaction[] pending = match(MatchArg.and(MatchArg.equals("status", "PENDING"), 
				MatchArg.notEquals("transaction_type", "DEPOSIT"),
				MatchArg.notEquals("transaction_type", "WITHDRAW")));
		for (Transaction t : pending) {
			if (t.getTransaction_type().equals("DEPOSIT")) {
				Customer user = customerDAO.read(t.getCustomer_id());
				if (user == null)
					continue;
				FundPriceHistory price = histDAO.getPrice(t.getFund_id());
				if (price == null)
					continue;
				long sellAmount = (price.getPrice() / 100) * (t.getShares() / 1000);
				user.setCash(user.getCash() + sellAmount);
				customerDAO.update(user);
			}
			t.setStatus("APPROVED");
		}
	}
}
