package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import databeans.*;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

public class Model {
	private CustomerDAO 					customerDAO;
	private FundDAO 						fundDAO;
	private GenericDAO<Employee> 			employeeDAO;
	private GenericDAO<FundPriceHistory>	fundHistDAO;
	private GenericDAO<Transaction>			transDAO;
	private GenericDAO<Position>			posDAO;
	
	

	public Model(ServletConfig config) throws ServletException {
		String jdbcDriver = config.getInitParameter("jdbcDriverName");
		String jdbcURL    = config.getInitParameter("jdbcURL");
		System.out.println("Debug: " + jdbcDriver + " " + jdbcURL);
		
		ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
		
		try {
			customerDAO = new CustomerDAO(pool, "Customer");
			fundDAO = new FundDAO(pool, "Fund");
			employeeDAO = new GenericDAO<Employee>(Employee.class, "Employee", pool);
			fundHistDAO = new GenericDAO<FundPriceHistory>(FundPriceHistory.class, "Fund_Price_History", pool);
			transDAO = new GenericDAO<Transaction>(Transaction.class, "Transaction", pool);
			posDAO = new GenericDAO<Position>(Position.class, "Position", pool);
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CustomerDAO getCustomerDAO() { return customerDAO; }
	public FundDAO getFundDAO() { return fundDAO; }
	public GenericDAO<Employee> getEmployeeDAO() { return employeeDAO; }
	public GenericDAO<FundPriceHistory> getFundHistDAO() { return fundHistDAO; }
	public GenericDAO<Transaction> getTransDAO() { return transDAO; }
	public GenericDAO<Position> getPosDAO() { return posDAO; }
}