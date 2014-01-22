package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.Customer;
import databeans.Position;

public class PosDAO extends GenericDAO<Position>{
	public PosDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(Position.class, tableName, pool);
	}
	
	public Position lookup(int customer_id, int fund_id) throws RollbackException {
        try {
        	Transaction.begin();
			ArrayList<Position> arr = new ArrayList<Position>();
			Position cur = read(customer_id);
			
			
			
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
