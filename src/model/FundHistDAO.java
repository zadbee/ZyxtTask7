package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databeans.FundPriceHistory;

public class FundHistDAO extends GenericDAO<FundPriceHistory>{

	public FundHistDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(FundPriceHistory.class, tableName, pool);
	}

}
