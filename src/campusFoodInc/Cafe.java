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
	public void buy(CampusCard theCard) throws Exception {
		theCard.buy(cart);
		int size = cart.size();
		while (size > 0) {
			//theCard.buy(cart.get(0));
			cart.remove(0);
			size--;
		}
	}

	public void addToCart(Food theFood) {
		cart.add(theFood);
	}

	public void removeFromCart(Food theFood) {
		cart.remove(theFood);
	}

	public void reset() {
		cart.removeAll(cart);
	}

	/*
	 * Add Item to Cart
	 */
	public void orderFood(Food theFood) {
		cart.add(theFood);
	}
}
