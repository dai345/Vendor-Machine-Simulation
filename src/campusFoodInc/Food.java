package campusFoodInc;

public class Food {

	String name;
	double price;
	double cal;
	double sodium;
	double sugar;

	public Food() {
		name = "No Name";
		price = 0;
		cal = 0;
		sodium = 0;
		sugar = 0;
	}

	public Food(String name, double price, double cal, double sod, double sug) {
		this.name = name;
		this.price = price;
		this.cal = cal;
		this.sodium = sod;
		this.sugar = sug;
	}

	// return a string of food's information
	public String getFood() {
		String s = "";
		s = "Name: " + name + "Price: " + price + "Calory: " + cal + "Sodium: "
				+ sodium + "Sugar: " + sugar;
		return s;
	}

	public boolean equals(Food compare) {
		if (name.equals(compare.getName()) && (price == compare.getPrice())
				&& (cal == compare.getCal()) && (sodium == compare.getSod())
				&& sugar == compare.getSug())
			return true;
		else
			return false;
	}

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public double getCal() {
		return cal;
	}

	public double getSod() {
		return sodium;
	}

	public double getSug() {
		return sugar;
	}
}
