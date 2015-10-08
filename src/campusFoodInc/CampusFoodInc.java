package campusFoodInc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import campusCard.CampusCard;

abstract public class CampusFoodInc {
	// Coordinates of the Restaurant
	int x, y;
	String name;
	ArrayList<Food> food;
	private ImageIcon theImage = null;

	public void setImage(ImageIcon theImage) {
		this.theImage = theImage;
	}

	public ImageIcon getImage() {
		return theImage;
	}
	public void addRandomFood() {
		Random r = new Random();

		String[] ary = new String[24];
		String content = null;
		String src = "dat/food.txt";
		int i = 0;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(src));
			while (i < 24) {
				content = bf.readLine();
				ary[i] = content;
				i++;
			}
			boolean retry = true;
			do {
				StringBuilder sb = new StringBuilder();
				int randomNum = r.nextInt(24);
				sb.append(ary[randomNum]);
				String[] food1 = sb.toString().trim().split(":");
				Food newFood = new Food(food1[0], Double.valueOf(food1[1])
						.doubleValue(), Double.valueOf(food1[2]).doubleValue(),
						Double.valueOf(food1[3]).doubleValue(), Double.valueOf(
								food1[4]).doubleValue());
				int j = 0;
				for (j = 0; j < food.size(); j++)
					if (food.get(j).equals(newFood))
						break;
				if (j == food.size()) {
					food.add(newFood);
					retry = false;
				}
			} while (retry);
			bf.close();
		} catch (Exception e) {
		}
	}

	public int getNumFood() {
		return food.size();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public CampusFoodInc() {
		this(0, 0, "No Name Inc");
	}

	public Food getFood(int i) {
		return food.get(i);
	}

	public Food getFood(String name) throws Exception {
		for (Food a : food)
			if (a.getName().equals(name))
				return a;
		throw new Exception("No Such Food");
	}

	public CampusFoodInc(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.food = new ArrayList<Food>();
		for (int i = 0; i < 9; i++)
			addRandomFood();
	}

	// display available food of the CampusCafeInc
	public void diplayAvailableFood() {
		for (int i = 0; i < food.size(); i++) {
			System.out.print(food.get(i).name + " ");
		}
	}

	public String toString() {
		return name;
	}

	abstract public void addToCart(Food theFood);

	abstract public void removeFromCart(Food theFood);

	abstract public void buy(CampusCard theCard) throws Exception;

	abstract public void reset();

	public double getPrice(int i) {
		return food.get(i).price;
	}
}
