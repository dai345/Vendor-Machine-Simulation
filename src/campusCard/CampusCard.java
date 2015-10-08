package campusCard;

import java.util.ArrayList;

import campusFoodInc.DietaryType;
import campusFoodInc.Food;

public class CampusCard {
	DietaryProfile dP;
	ExpenseProfile eP;
	PersonalInfo pI;
	String cardID;
	double funds;

	public void deserializeExpenseProfile(String pathToFile) {
		eP.deserialize(pathToFile);
	}

	public void deserializeDietaryProfile(String pathToFile) {
		dP.deserialize(pathToFile);
	}

	public void serializeExpenseProfile(String pathToFile) {
		eP.serialize(pathToFile);
	}

	public void serializeDietaryProfile(String pathToFile) {
		dP.serialize(pathToFile);
	}

	public void setDietaryProfile(DietaryProfile dP) {
		this.dP = dP;
	}

	public void setPersonalInfo(PersonalInfo pI) {
		this.pI = pI;
	}

	public DietaryProfile getDietaryProfile() {
		return this.dP;
	}

	public ExpenseProfile getExpenseProfile() {
		return this.eP;
	}

	public PersonalInfo getPersonalInfo() {
		return this.pI;
	}

	public String getName() {
		return this.pI.getName();
	}

	public double getCal() {
		if (this.dP.isSet())
			return this.dP.getLimit(DietaryType.CAL);
		else
			return Double.NaN;
	}

	public double getSug() {
		if (this.dP.isSet())
			return this.dP.getLimit(DietaryType.SUG);
		else
			return Double.NaN;
	}

	public double getSod() {
		if (this.dP.isSet())
			return this.dP.getLimit(DietaryType.SOD);
		else
			return Double.NaN;
	}

	public double getFund() {
		return funds;
	}

	public void setFund(double funds) {
		this.funds = funds;
	}

	public void addFund(double funds) {
		if (funds < 0)
			funds = 0;
		this.funds += funds;
	}

	public void addCal(double cal) {
		dP.addCal(cal);
	}

	public void addSug(double sug) {
		dP.addSug(sug);
	}

	public void addSod(double sod) {
		dP.addSod(sod);
	}

	public String getCardID() {
		return this.cardID;
	}

	public void buy(Food theFood) throws Exception {
		if (funds - theFood.getPrice() < 0)
			throw new Exception("Funds Underflow");
		funds -= theFood.getPrice();
		dP.use(theFood);
		dP.commit(theFood);
		eP.commit(theFood);
	}

	public void buy(ArrayList<Food> cart) throws Exception {
		double amount = eP.commit(cart);
		if (funds - amount < 0)
			throw new Exception("Funds Underflow");
		funds -= amount;
		dP.commit(cart);
	}

	public CampusCard() {
		this(new PersonalInfo("ANON", "YMOUS"), "SUPER");
	}

	public CampusCard(PersonalInfo pI, String cardID) {
		this.cardID = cardID;
		this.pI = pI;
		funds = 0;
		dP = new DietaryProfile();
		eP = new ExpenseProfile();
	}
}
