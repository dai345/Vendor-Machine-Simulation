package campusFoodInc;

import campusCard.CampusCard;

public class VendingMachine extends CampusFoodInc {

	Food chosenFood;

	public VendingMachine() {
		this(0, 0, "No Name Vend");
	}

	public VendingMachine(int x, int y, String name) {
		super(x, y, name);
	}

	/*
	 * Buy food via a vending machine.
	 */
	public void buy(CampusCard inputCard) throws Exception {
		if (chosenFood == null)
			return;
		inputCard.buy(chosenFood);
		chosenFood = null;
	}

	public void addToCart(Food theFood) {
		chosenFood = theFood;
	}

	public void reset() {
		chosenFood = null;
	}

	public void removeFromCart(Food theFood) {
		chosenFood = null;
	}

}
