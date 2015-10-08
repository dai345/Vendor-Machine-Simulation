package campusFoodInc;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.ImageIcon;

public class CampusMap {

	private CampusFoodInc[] cafeAndVendSet;

	public CampusMap() {
		cafeAndVendSet = new CampusFoodInc[5];
		try {
			BufferedReader bf = new BufferedReader(new FileReader(
					"dat/store.txt"));
			for (int i = 0; i < 5; i++) {
				String[] storeInfo = bf.readLine().trim().split(":");
				if (storeInfo[1].equals("Cafe"))
					cafeAndVendSet[i] = new Cafe(
							Integer.parseInt(storeInfo[2]),
							Integer.parseInt(storeInfo[3]), storeInfo[4]);
				else
					cafeAndVendSet[i] = new VendingMachine(
							Integer.parseInt(storeInfo[2]),
							Integer.parseInt(storeInfo[3]), storeInfo[4]);
				cafeAndVendSet[i].setImage(new ImageIcon("dat/store/" + storeInfo[0] + ".png"));
			}
			bf.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public CampusFoodInc[] getStoreSet() {
		return cafeAndVendSet;
	}
}
