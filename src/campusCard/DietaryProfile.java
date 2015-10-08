package campusCard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

import campusFoodInc.DietaryType;
import campusFoodInc.Food;

@SuppressWarnings("serial")
public class DietaryProfile implements Serializable {
	private boolean isSet;
	private double caloryLimit, sugarsLimit, sodiumLimit;
	ArrayList<Double> cal;
	ArrayList<Double> sug;
	ArrayList<Double> sod;

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
			DietaryProfile theReadObject = (DietaryProfile) inStream
					.readObject();
			this.cal = theReadObject.getHistory(DietaryType.CAL);
			this.sug = theReadObject.getHistory(DietaryType.SUG);
			this.sod = theReadObject.getHistory(DietaryType.SOD);
			this.caloryLimit = theReadObject.getLimit(DietaryType.CAL);
			this.sugarsLimit = theReadObject.getLimit(DietaryType.SUG);
			this.sodiumLimit = theReadObject.getLimit(DietaryType.SOD);
			this.isSet = theReadObject.isSet();
			inStream.close();
		} catch (Exception e) {
			System.out.println("Creating New DietaryProfile");
			this.cal = new ArrayList<Double>();
			this.sug = new ArrayList<Double>();
			this.sod = new ArrayList<Double>();
			caloryLimit = sugarsLimit = sodiumLimit = 0;
			isSet = true;
		}
	}

	private class BarGraph extends JPanel {
		private int numOfBars;

		public BarGraph() {
			numOfBars = cal.size();
			if (numOfBars > 10)
				numOfBars = 10;
		}

		protected void paintComponent(Graphics gIn) {
			super.paintComponent(gIn);
			Graphics2D g = (Graphics2D) gIn;
			g.setFont(new Font("Courier", 1, 18));
			int choice = cal.size() - 1;
			int scalingFactor = 15;
			int height = 500;
			int width = 15;
			int shift = 25;
			g.drawString("Latest Dietary Information", 320, 50);
			g.drawString("Cal", 250, height + 140);
			g.drawString("Sug", 350, height + 140);
			g.drawString("Sod", 450, height + 140);
			g.setColor(Color.ORANGE);
			g.fillRect(215, height + 120, 25, 25);
			g.setColor(Color.BLUE);
			g.fillRect(315, height + 120, 25, 25);
			g.setColor(Color.GREEN);
			g.fillRect(415, height + 120, 25, 25);
			for (int i = numOfBars - 1; i >= 0; i--) {
				g.setColor(Color.ORANGE);
				g.fillRect((i + 1) * 75 + width * 0, height - scalingFactor
						* cal.get(choice).intValue(), width, scalingFactor
						* cal.get(choice).intValue());
				g.drawString(cal.get(choice).toString(), (i + 1) * 75, height
						+ shift * 1);
				g.setColor(Color.BLUE);
				g.fillRect((i + 1) * 75 + width * 1, height - scalingFactor
						* sug.get(choice).intValue(), width, scalingFactor
						* sug.get(choice).intValue());
				g.drawString(sug.get(choice).toString(), (i + 1) * 75, height
						+ shift * 2);
				g.setColor(Color.GREEN);
				g.fillRect((i + 1) * 75 + width * 2, height - scalingFactor
						* sod.get(choice).intValue(), width, scalingFactor
						* sod.get(choice).intValue());
				g.drawString(sod.get(choice).toString(), (i + 1) * 75, height
						+ shift * 3);
				choice--;
			}
		}
	}

	public JPanel drawGraph() {
		return new BarGraph();
	}

	public void commit(Food theFood) {
		cal.add(new Double(theFood.getCal()));
		sug.add(new Double(theFood.getSug()));
		sod.add(new Double(theFood.getSod()));
	}

	public void commit(ArrayList<Food> theFood) throws Exception {
		double totCal = 0;
		double totSug = 0;
		double totSod = 0;
		for (int i = 0; i < theFood.size(); i++) {
			totCal += theFood.get(i).getCal();
			totSug += theFood.get(i).getSug();
			totSod += theFood.get(i).getSod();
		}
		if (totCal > caloryLimit || totSug > sugarsLimit
				|| totSod > sodiumLimit)
			throw new Exception("Dietary Underflow");
		caloryLimit -= totCal;
		sugarsLimit -= totSug;
		sodiumLimit -= totSod;
		cal.add(totCal);
		sug.add(totSug);
		sod.add(totSod);
	}

	public DietaryProfile() {
		caloryLimit = sodiumLimit = sugarsLimit = 0;
		cal = new ArrayList<Double>();
		sug = new ArrayList<Double>();
		sod = new ArrayList<Double>();
		this.isSet = false;
	}

	public DietaryProfile(double cal, double sug, double sod) {
		this.caloryLimit = cal;
		this.sugarsLimit = sug;
		this.sodiumLimit = sod;
		this.cal = new ArrayList<Double>();
		this.sug = new ArrayList<Double>();
		this.sod = new ArrayList<Double>();
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

	public double getLimit(DietaryType type) {
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

	public ArrayList<Double> getHistory(DietaryType type) {
		switch (type) {
		case CAL:
			return this.cal;
		case SOD:
			return this.sod;
		case SUG:
		default:
			return this.sug;
		}
	}

	public void addCal(double cal) {
		if (!isSet) {
			isSet = true;
			this.caloryLimit = 0;
		}
		if (cal < 0)
			cal = 0;
		this.caloryLimit += cal;
	}

	public void addSug(double sug) {
		if (!isSet) {
			isSet = true;
			this.sugarsLimit = 0;
		}
		if (sug < 0)
			sug = 0;
		this.sugarsLimit += sug;
	}

	public void addSod(double sod) {
		if (!isSet) {
			isSet = true;
			this.sodiumLimit = 0;
		}
		if (sod < 0)
			sod = 0;
		this.sodiumLimit += sod;
	}
}
