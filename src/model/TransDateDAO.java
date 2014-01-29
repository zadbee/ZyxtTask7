package model;

import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.TransitionDate;

public class TransDateDAO extends GenericDAO<TransitionDate>{
	public TransDateDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(TransitionDate.class, tableName, pool);
	}
	
	public Date getLastTransitionDay() throws RollbackException {
		TransitionDate[] dates = match(MatchArg.max("date"));
		if (dates == null || dates.length == 0)
			return null;
		return dates[0].getDate();
	}
}
