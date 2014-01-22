package databeans;

import org.genericdao.PrimaryKey;

//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;


@PrimaryKey("customer_id" "fund_id")

public class Position {
	private int    	customer_id  = -1;
	private String 	fund_id 	 = null; 
	private int 	shares     	 = -1;
	
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getFund_id() {
		return fund_id;
	}
	public void setFund_id(String fund_id) {
		this.fund_id = fund_id;
	}
	public int getShares() {
		return shares;
	}
	public void setShares(int shares) {
		this.shares = shares;
	}
	
}
