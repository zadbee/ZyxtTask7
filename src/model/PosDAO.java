package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databeans.Position;

public class PosDAO extends GenericDAO<Position>{
	public PosDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(Position.class, tableName, pool);
	}
}
