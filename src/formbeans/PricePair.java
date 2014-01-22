package formbeans;

public class PricePair {
	int id = -1;
	int oldprice = 0;
	int price = 0;
	public PricePair(int _id, int _oldprice) {
		id = _id;
		oldprice = _oldprice;
	}
	public void setPrice(int _price) { price = _price; }
}
