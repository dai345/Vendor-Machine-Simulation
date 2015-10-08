package campusFoodInc;

public class CampusMap {

	private CampusFoodInc[] cafeAndVendSet;

	public CampusMap() {
		cafeAndVendSet = new CampusFoodInc[8];
		cafeAndVendSet[0] = new Cafe(1, 2, "1st");
		cafeAndVendSet[1] = new Cafe(3, 4, "2nd");
		cafeAndVendSet[2] = new Cafe(5, 6, "3th");
		cafeAndVendSet[3] = new Cafe(7, 8, "4th");
		cafeAndVendSet[4] = new VendingMachine(9, 10, "5th");
		cafeAndVendSet[5] = new VendingMachine(11, 12, "6th");
		cafeAndVendSet[6] = new VendingMachine(13, 14, "7th");
		cafeAndVendSet[7] = new VendingMachine(15, 16, "8th");
	}

	// 2.1Display a Campus map showing the location of the cafes, food stores
	// and vending machines on campus.
	public void display() {
		for (int i = 0; i < cafeAndVendSet.length; i++) {
			System.out.println("Address: " + cafeAndVendSet[i].name + ", "
					+ "X: " + cafeAndVendSet[i].x + ", " + "Y: "
					+ cafeAndVendSet[i].y);
		}
	}
	
	public CampusFoodInc[] getStoreSet() {
		return cafeAndVendSet;
	}

	// add EventHandler
	// choose a particular campusCafe
	public CampusFoodInc selectCafe(int i) {
		return cafeAndVendSet[i];
	}
	public static void main(String[] args) {
		CampusMap a = new CampusMap();
		for(int i=0; i<8; i++)
			System.out.println(a.getStoreSet()[i].getName());
	}
}
