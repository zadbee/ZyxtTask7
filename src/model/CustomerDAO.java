//I am trying to test git


package model;


import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.Customer;

public class CustomerDAO extends GenericDAO<Customer> {
	public CustomerDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(Customer.class, tableName, pool);
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
}
