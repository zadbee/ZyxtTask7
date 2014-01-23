package model;

import java.util.ArrayList;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Fund;

public class FundDAO extends GenericDAO<Fund> {
	public FundDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(Fund.class, tableName, pool);
	}
	
	public Fund readBySymbol (String symbol) throws RollbackException {
		Fund[] tmp = match(MatchArg.equals("symbol",symbol));
		if (tmp == null || tmp.length == 0)
			return null;
		else
			return tmp[0];
	}
			
			
	public Fund readByName(String name) throws RollbackException {
		Fund[] tmp = match(MatchArg.equals("name", name));
		if (tmp == null || tmp.length == 0)
			return null;
		else
			return tmp[0];
	}

	
	public ArrayList<Fund> getAll() throws RollbackException{
		Fund[] tmp = match();
		ArrayList<Fund> arrFund = new ArrayList<Fund>();
		for(Fund x : tmp){
			arrFund.add(x);
		}
		return arrFund;
	}

}