package model;

import java.util.ArrayList;
import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.FundPriceHistory;

public class FundHistDAO extends GenericDAO<FundPriceHistory>{
	public FundHistDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(FundPriceHistory.class, tableName, pool);
	}
	
	public void updateAll(ArrayList<FundPriceHistory> prices) throws RollbackException {
		Date date = new Date();
		for (FundPriceHistory f : prices) {
			f.setPrice_date(date);
			createAutoIncrement(f);
		}
	}
	
	public ArrayList<FundPriceHistory> getAll() throws RollbackException {
		ArrayList<FundPriceHistory> ret = new ArrayList<FundPriceHistory>();
		FundPriceHistory[] tmp = match(MatchArg.max("price_date"));
		for (FundPriceHistory f : tmp)
			ret.add(f);
		return ret;
	}
	
	public FundPriceHistory getPrice(int id) throws RollbackException {
		FundPriceHistory[] tmp = match(MatchArg.and(MatchArg.max("price_date"), MatchArg.equals("fund_id", id)));
		if (tmp == null || tmp.length == 0)
			return null;
		return tmp[0];
	}
	
	public ArrayList<FundPriceHistory> getFundHist(int id) throws RollbackException {
		ArrayList<FundPriceHistory> ret = new ArrayList<FundPriceHistory>();
		FundPriceHistory[] tmp = match(MatchArg.equals("fund_id", id));
		for (FundPriceHistory f : tmp)
			ret.add(f);
		return ret;
	}	
}
