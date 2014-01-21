package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databeans.Employee;



public class EmployeeDAO extends GenericDAO<Employee>{
	public EmployeeDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(Employee.class, tableName, pool);
	}
}
