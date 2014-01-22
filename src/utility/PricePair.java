package utility;

public class PricePair {
	int id = -1;
	int price = 0;
	public PricePair(int _id) {
		id = _id;
	}
	public void setPrice(int _price) { price = _price; }
	public int getId() { return id; }
}
