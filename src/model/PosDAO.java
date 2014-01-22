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
	
//	public Position lookup(int customer_id, int fund_id) throws RollbackException {
//        try {
//        	Transaction.begin();
//			ArrayList<Position> arr = new ArrayList<Position>();
//			Position cur = read(customer_id);
//			
//			
//			
//			if (dbCustomer == null) {
//				throw new RollbackException("UserID "+userid+" no longer exists");
//			}
//			
//			dbCustomer.setPassword(password);
//			
//			update(dbCustomer);
//			Transaction.commit();
//		} finally {
//			if (Transaction.isActive()) Transaction.rollback();
//		}
//	}
	
	public Position lookup(int customer_id, int fund_id) throws MyDAOException {

		try {
			Transaction.begin();
			
			ArrayList<Position> arr = new ArrayList<Position>(); match();
			
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
					+ tableName + " WHERE customer_id=? and fund_id=?");
			pstmt.setInt(1, customer_id);
			pstmt.setInt(2, fund_id);			
			ResultSet rs = pstmt.executeQuery();

			Position position;
			if (!rs.next()) {
				position = null;
			} else {
				position = new Position();				
				position.setCustomer_id(rs.getInt("customer_id"));
				position.setFund_id(rs.getInt("fund_id"));
				position.setShares((double)rs.getLong("shares")*1.0/1000);

			}

			rs.close();
			pstmt.close();
			releaseConnection(con);
			return position;

		} catch (SQLException e) {
            try { 
            	if (con != null) 
            		con.close(); 
            } 
            catch (SQLException e2) {
            	
            }
            throw new MyDAOException(e);
		}

	}

}
