package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databeans.Transaction;

public class TransDAO extends GenericDAO<Transaction> {

	public TransDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(Transaction.class, tableName, pool);
	}
	
	public void clearPending() {
		
	}
}
