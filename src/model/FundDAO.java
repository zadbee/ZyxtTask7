package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Fund;
import databeans.Position;

public class FundDAO extends GenericDAO<Fund> {
	public FundDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(Fund.class, tableName, pool);
	}
	
	public Fund readBySymbol (String symbol) throws RollbackException {
		Fund[] tmp = match(MatchArg.equals("symbol",symbol));
		if (tmp.length == 0)
			return null;
		else
			return tmp[0];
	}
}