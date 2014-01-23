package model;


import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.Customer;

public class CustomerDAO extends GenericDAO<Customer> {
	public CustomerDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(Customer.class, tableName, pool);
	}
	public Customer readByName(String name) throws RollbackException {
		Customer[] tmp = match(MatchArg.equals("username", name));
		if (tmp.length == 0)
			return null;
		else
			return tmp[0];
	}
	
	public void setPassword(int userid, String password) throws RollbackException {
        try {
        	Transaction.begin();
			Customer dbCustomer = read(userid);
			
			if (dbCustomer == null) {
				throw new RollbackException("UserID "+userid+" no longer exists");
			}
			
			dbCustomer.setPassword(password);
			
			update(dbCustomer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	public void setPassword(String username, String password) throws RollbackException {
        try {
        	Transaction.begin();
			Customer dbCustomer = read(username);
			
			if (dbCustomer == null) {
				throw new RollbackException("Username "+username+" no longer exists");
			}
			
			dbCustomer.setPassword(password);
			
			update(dbCustomer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
		public void setCash(int userid, Long cash) throws RollbackException {
        try {
        	Transaction.begin();
			Customer dbCustomer = read(userid);
			
			if (dbCustomer == null) {
				throw new RollbackException("UserID "+userid+" no longer exists");
			}
			
			dbCustomer.setCash(cash);
			
			update(dbCustomer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
		/*public void setBalance(int userid, Long balance) throws RollbackException {
	        try {
	        	Transaction.begin();
				Customer dbCustomer = read(userid);
				
				if (dbCustomer == null) {
					throw new RollbackException("UserID "+userid+" no longer exists");
				}
				
				dbCustomer.setBalance(balance);
				
				update(dbCustomer);
				Transaction.commit();
			} finally {
				if (Transaction.isActive()) Transaction.rollback();
			}
		}*/
}
