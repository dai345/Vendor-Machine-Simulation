package campusFoodInc;

import java.util.ArrayList;
import campusCard.CampusCard;

public class Cafe extends CampusFoodInc {

	ArrayList<Food> cart;

	public Cafe() {
		this(0, 0, "No Name Cafe");
	}

	public Cafe(int x, int y, String name) {
		super(x, y, name);
		cart = new ArrayList<Food>();
	}

	/*
	 * Buy the Foods in the Cart
	 */
	public void buyFood(CampusCard myCard) throws Exception {
		for (int i = 0; i < cart.size(); i++)
			myCard.buy(cart.get(i).price, cart.get(i));
	}

	/*
	 * Add Item to Cart
	 */
	public void orderFood(Food theFood) {
		cart.add(theFood);
	}
}
