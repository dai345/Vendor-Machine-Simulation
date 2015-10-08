package campusCard;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import campusFoodInc.Food;

@SuppressWarnings("serial")
public class ExpenseProfile extends JFrame implements Serializable {
	private ArrayList<Double> funds;

	public void serialize(String pathToFile) {
		try {
			ObjectOutputStream ouStream = new ObjectOutputStream(
					new FileOutputStream(pathToFile, false));
			ouStream.writeObject(this);
			ouStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deserialize(String pathToFile) {
		try {
			ObjectInputStream inStream = new ObjectInputStream(
					new FileInputStream(pathToFile));
			ExpenseProfile theReadObject = (ExpenseProfile) inStream.readObject();
			this.funds = theReadObject.getFunds();
			inStream.close();
		} catch (Exception e) {
			System.out.println("Creating New ExpenseProfile");
			this.funds = new ArrayList<Double>();
		}
	}

	public ArrayList<Double> getFunds() {
		return funds;
	}

	public int getNumOfPurchase() {
		return funds.size();
	}

	private class BarGraph extends JPanel {
		private int numOfBars;

		public BarGraph() {
			numOfBars = funds.size();
			if (numOfBars > 10)
				numOfBars = 10;
		}

		protected void paintComponent(Graphics gIn) {
			super.paintComponent(gIn);
			Graphics2D g = (Graphics2D) gIn;
			g.setFont(new Font("Courier", 1, 18));
			int choice = funds.size() - 1;
			int scalingFactor = 30;
			g.drawString("Latest Purchases", 350, 50);
			for (int i = numOfBars - 1; i >= 0; i--) {
				g.fillRect((i + 1) * 75, 600 - scalingFactor
						* funds.get(choice).intValue(), 50, scalingFactor
						* funds.get(choice).intValue());
				g.drawString("$" + funds.get(choice).toString(), (i + 1) * 75,
						625);
				choice--;
			}
		}
	}

	public JPanel drawGraph() {
		return new BarGraph();
	}

	public void commit(Food theFood) {
		funds.add(new Double(theFood.getPrice()));
	}

	public double commit(ArrayList<Food> cart) {
		Double amount = new Double(0);
		for (int i = 0; i < cart.size(); i++)
			amount += cart.get(i).getPrice();
		funds.add(new Double(amount));
		return amount;
	}

	public ExpenseProfile() {
		funds = new ArrayList<Double>();
	}

}
