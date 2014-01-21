package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databeans.Fund;

public class FundDAO extends GenericDAO<Fund> {
	public FundDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(Fund.class, tableName, pool);
	}
}