package databeans;

//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;

import org.genericdao.PrimaryKey;


@PrimaryKey("fund_id")
public class Fund {
//	public static final List<String> EXTENSIONS = Collections.unmodifiableList(Arrays.asList( new String[] {
//			".jpg", ".gif", ".JPG"
//	} ));

	private int    fund_id     = 0;
	private String name		   = null; 
	private String symbol     = null;
	// Easier for querying the price.
	private int price = 0;
	
	public int getFund_id() {
		return fund_id;
	}
	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public int getPrice() { return price; }
	public void setPrice(int _price) { price = _price; }
}
