package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Customer;
import databeans.Fund;

public class FundDAO extends GenericDAO<Fund> {
	public FundDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(Fund.class, tableName, pool);
	}
	
	public Fund readByName(String name) throws RollbackException {
		Fund[] tmp = match(MatchArg.equals("name", name));
		if (tmp == null || tmp.length == 0)
			return null;
		else
			return tmp[0];
	}
}