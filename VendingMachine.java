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
	public void buyFood(CampusCard inputCard) throws Exception {
		inputCard.buy(chosenFood.getPrice(), chosenFood);
	}

	/*
	 * Chooses a Food
	 */
	public void orderFood(Food theFood) throws Exception {
		chosenFood = theFood;
	}
}
