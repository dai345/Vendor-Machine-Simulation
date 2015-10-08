package campusFoodInc;

public class FoodPreference {
	private boolean isSet;
	private double caloryLimit, sodiumLimit, sugarsLimit;

	public FoodPreference() {
		caloryLimit = sodiumLimit = sugarsLimit = 0;
		this.isSet = false;
	}

	public FoodPreference(double cal, double sod, double sug) {
		this.caloryLimit = cal;
		this.sodiumLimit = sod;
		this.sugarsLimit = sug;
		this.isSet = true;
	}

	public boolean isSet() {
		return this.isSet;
	}

	public double use(DietaryType type, double amount) throws Exception {
		switch (type) {
		case CAL:
			if (this.caloryLimit - amount < 0)
				break;
			this.caloryLimit -= amount;
			return this.caloryLimit;
		case SOD:
			if (this.sodiumLimit - amount < 0)
				break;
			this.sodiumLimit -= amount;
			return this.sodiumLimit;
		case SUG:
		default:
			if (this.sugarsLimit - amount < 0)
				break;
			this.sugarsLimit -= amount;
			return this.sugarsLimit;
		}
		throw new Exception("Underflow");
	}

	public void use(Food theFood) throws Exception {
		if (!this.isSet)
			return;
		if (this.caloryLimit - theFood.getCal() < 0)
			throw new Exception("Cal Underflow");
		if (this.sodiumLimit - theFood.getSod() < 0)
			throw new Exception("Sod Underflow");
		if (this.sugarsLimit - theFood.getSug() < 0)
			throw new Exception("Sug Underflow");
		this.caloryLimit -= theFood.getCal();
		this.sodiumLimit -= theFood.getSod();
		this.sugarsLimit -= theFood.getSug();
	}

	public double get(DietaryType type) {
		switch (type) {
		case CAL:
			return this.caloryLimit;
		case SOD:
			return this.sodiumLimit;
		case SUG:
		default:
			return this.sugarsLimit;
		}
	}
}
