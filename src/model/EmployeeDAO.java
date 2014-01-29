package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.Employee;

public class EmployeeDAO extends GenericDAO<Employee>{
	public EmployeeDAO(ConnectionPool pool, String tableName) throws DAOException {
		super(Employee.class, tableName, pool);
	}
	
	public void setPassword(String username, String password){
        try {
        	Transaction.begin();
			Employee dbEmployee = read(username);
			
			if (dbEmployee == null) {
				throw new RollbackException("Username "+username+" no longer exists");
			}
			
			dbEmployee.setPassword(password);			
			update(dbEmployee);
			Transaction.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
