package databeans;

//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;

import java.util.Date;

import org.genericdao.PrimaryKey;


@PrimaryKey("fund_id")
public class FundPriceHistory {
//	public static final List<String> EXTENSIONS = Collections.unmodifiableList(Arrays.asList( new String[] {
//			".jpg", ".gif", ".JPG"
//	} ));

	private int    fund_id          = -1;
	private Date price_date		   = null; 
	private Long price     = 0L;
	public int getFund_id() {
		return fund_id;
	}
	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}
	public Date getPrice_date() {
		return price_date;
	}
	public void setPrice_date(Date price_date) {
		this.price_date = price_date;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
}
