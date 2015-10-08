package graphicsUserInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import campusCard.CampusCard;
import campusCard.DietaryProfile;
import campusCard.PersonalInfo;
import campusFoodInc.Cafe;
import campusFoodInc.CampusFoodInc;
import campusFoodInc.CampusMap;
import campusFoodInc.Food;
import campusFoodInc.VendingMachine;

@SuppressWarnings("serial")
public class SmartCampusGUI extends JFrame {
	private CampusMap theMap;
	private CampusCard thisCard;
	private PersonalProfilePanel pp;
	private DietaryPanel dp;
	private ExpensePanel ep;
	private MapDisplayPanel mp;
	private StoreDisplayPanel sp;
	private JTabbedPane tabular = new JTabbedPane();

	private class PersonalProfilePanel extends JPanel {
		private DisplayPanel displayPanel;
		private FormsPanel formsPanel;

		public void refresh() {
			displayPanel.updateAll();
			formsPanel.updateAll();
		}

		private class FormsPanel extends JPanel {
			// Fields for Profile Information
			JTextField theFundsField, theCalField, theSugField, theSodField,
					fNameField, lNameField;
			JLabel theFundsLabel, theCalLabel, theSugLabel, theSodLabel,
					theNameLabel;
			JButton submitButton;

			public void updateAll() {
				theFundsField.setText(Double.toString(thisCard.getFund()));
				theCalField.setText(Double.toString(thisCard.getCal()));
				theSugField.setText(Double.toString(thisCard.getSug()));
				theSodField.setText(Double.toString(thisCard.getSod()));
				fNameField.setText(thisCard.getPersonalInfo().getFName());
				lNameField.setText(thisCard.getPersonalInfo().getLName());
			}

			public FormsPanel() {
				this.setLayout(new GridBagLayout());
				GridBagConstraints gc = new GridBagConstraints();
				// Initialize the Name Field and Label
				fNameField = new JTextField(thisCard.getPersonalInfo()
						.getFName(), 16);
				lNameField = new JTextField(thisCard.getPersonalInfo()
						.getLName(), 16);
				theNameLabel = new JLabel("Name   : ");
				fNameField.setFont(new Font("Courier", 1, 20));
				lNameField.setFont(new Font("Courier", 1, 20));
				theNameLabel.setFont(new Font("Courier", 1, 20));
				// Initialize the Funds Field and Label
				theFundsField = new JTextField(Double.toString(thisCard
						.getFund()), 10);
				theFundsLabel = new JLabel("Funds  : ");
				theFundsField.setFont(new Font("Courier", 1, 20));
				theFundsLabel.setFont(new Font("Courier", 1, 20));
				// Initialize the Dietary Fields and Label
				theCalField = new JTextField(
						Double.toString(thisCard.getCal()), 10);
				theSugField = new JTextField(
						Double.toString(thisCard.getSug()), 10);
				theSodField = new JTextField(
						Double.toString(thisCard.getSod()), 10);
				theCalLabel = new JLabel("Calory : ");
				theSugLabel = new JLabel("Sugar  : ");
				theSodLabel = new JLabel("Sodium : ");
				theCalField.setFont(new Font("Courier", 1, 20));
				theSugField.setFont(new Font("Courier", 1, 20));
				theSodField.setFont(new Font("Courier", 1, 20));
				theCalLabel.setFont(new Font("Courier", 1, 20));
				theSugLabel.setFont(new Font("Courier", 1, 20));
				theSodLabel.setFont(new Font("Courier", 1, 20));
				// Initialize Button
				submitButton = new JButton("Submit");
				submitButton.setFont(new Font("Courier", 1, 20));
				submitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (theCalField.getText().equals("")) {
							theCalField.setText("Invalid");
						} else if (theSugField.getText().equals("")) {
							theSugField.setText("Invalid");
						} else if (theSodField.getText().equals("")) {
							theSodField.setText("Invalid");
						} else if (theFundsField.getText().equals("")) {
							theFundsField.setText("Invalid");
						} else if (fNameField.getText().equals("")) {
							fNameField.setText("Invalid");
						} else if (lNameField.getText().equals("")) {
							lNameField.setText("Invalid");
						} else if (theCalField.getText().equals("Invalid")
								|| theSugField.getText().equals("Invalid")
								|| theSodField.getText().equals("Invalid")
								|| theFundsField.getText().equals("Invalid")
								|| fNameField.getText().equals("Invalid")
								|| lNameField.getText().equals("Invaid")) {
							// Do nothing in this case
						} else {
							thisCard.setDietaryProfile(new DietaryProfile(
									Double.parseDouble(theCalField.getText()),
									Double.parseDouble(theSugField.getText()),
									Double.parseDouble(theSodField.getText())));
							thisCard.setPersonalInfo(new PersonalInfo(
									fNameField.getText(), lNameField.getText()));
							thisCard.setFund(Double.parseDouble(theFundsField
									.getText()));
							displayPanel.updateAll();
						}

					}
				});
				// Add Row 1 (Name Part)
				gc.anchor = GridBagConstraints.WEST;
				gc.gridx = 0;
				gc.gridy = 0;
				this.add(theNameLabel, gc);
				gc.gridx++;
				this.add(fNameField, gc);
				gc.gridx++;
				this.add(lNameField, gc);
				// Add Row 2 (Fund Part)
				gc.gridx = 0;
				gc.gridy++;
				this.add(theFundsLabel, gc);
				gc.gridx++;
				this.add(theFundsField, gc);
				// Add Row 3 (Cal Part)
				gc.gridx = 0;
				gc.gridy++;
				this.add(theCalLabel, gc);
				gc.gridx++;
				this.add(theCalField, gc);
				// Add Row 4 (Sug Part)
				gc.gridx = 0;
				gc.gridy++;
				this.add(theSugLabel, gc);
				gc.gridx++;
				this.add(theSugField, gc);
				// Add Row 5 (Sod Part)
				gc.gridx = 0;
				gc.gridy++;
				this.add(theSodLabel, gc);
				gc.gridx++;
				this.add(theSodField, gc);
				// Add Row 6 (Button Part)
				gc.gridy++;
				gc.gridx++;
				this.add(submitButton, gc);

			}
		}

		private class DisplayPanel extends JPanel {
			// Labels for Profile Information
			JLabel theFundsLabel, theCalLabel, theSugLabel, theSodLabel,
					theCardIDLabel, theNameLabel;

			public DisplayPanel() {
				this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				this.setBorder(new EmptyBorder(25, 25, 5, 5));
				theFundsLabel = new JLabel();
				theCalLabel = new JLabel();
				theSugLabel = new JLabel();
				theSodLabel = new JLabel();
				theCardIDLabel = new JLabel("CardID : " + thisCard.getCardID());
				theNameLabel = new JLabel();
				theFundsLabel.setFont(new Font("Courier", 1, 20));
				theCalLabel.setFont(new Font("Courier", 1, 20));
				theSugLabel.setFont(new Font("Courier", 1, 20));
				theSodLabel.setFont(new Font("Courier", 1, 20));
				theCardIDLabel.setFont(new Font("Courier", 1, 20));
				theNameLabel.setFont(new Font("Courier", 1, 20));
				this.add(theNameLabel);
				this.add(theCardIDLabel);
				this.add(theFundsLabel);
				this.add(theCalLabel);
				this.add(theSugLabel);
				this.add(theSodLabel);
				updateAll();
			}

			public void updateAll() {
				theNameLabel.setText("Name   : " + thisCard.getName());
				theFundsLabel.setText("Funds  : " + thisCard.getFund());
				theCalLabel.setText("Calory : " + thisCard.getCal());
				theSugLabel.setText("Sugar  : " + thisCard.getSug());
				theSodLabel.setText("Sodium : " + thisCard.getSod());
			}
		}

		public PersonalProfilePanel() {
			this.setLayout(new BorderLayout());
			this.add(displayPanel = new DisplayPanel(), BorderLayout.NORTH);
			this.add(formsPanel = new FormsPanel(), BorderLayout.CENTER);
		}
	}

	private class DietaryPanel extends JPanel {
		public void refresh() {
			this.removeAll();
			JPanel theGraph = thisCard.getDietaryProfile().drawGraph();
			theGraph.setPreferredSize(new Dimension(900, 750));
			this.add(theGraph);
		}
		public DietaryPanel() {
			this.refresh();
		}
	}

	private class ExpensePanel extends JPanel {
		public void refresh() {
			this.removeAll();
			JPanel theGraph = thisCard.getExpenseProfile().drawGraph();
			theGraph.setPreferredSize(new Dimension(900, 750));
			this.add(theGraph);
		}

		public ExpensePanel() {
			this.refresh();
		}
	}

	private class MapDisplayPanel extends JPanel {
		List<JButton> theStoreButtons = new ArrayList<JButton>();

		public MapDisplayPanel() {
			// setSize(700, 650);
			setLayout(null);
			repaint();
			CampusFoodInc[] campusStoreSet = theMap.getStoreSet();
			for (int i = 0; i < campusStoreSet.length; i++) {
				theStoreButtons.add(new JButton(campusStoreSet[i].getImage()));
				theStoreButtons.get(i).setBounds(campusStoreSet[i].getX(),
						campusStoreSet[i].getY(),
						campusStoreSet[i].getImage().getIconWidth(),
						campusStoreSet[i].getImage().getIconHeight());
				JLabel storeName = new JLabel(campusStoreSet[i].getName());
				storeName.setBounds(campusStoreSet[i].getX(), campusStoreSet[i]
						.getY() + 60, campusStoreSet[i].getImage()
						.getIconWidth(), campusStoreSet[i].getImage()
						.getIconHeight());
				storeName.setFont(new Font("Courier", 1, 16));
				final int a = i;
				theStoreButtons.get(i).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sp.select(a);
						tabular.setSelectedComponent(sp);
					}
				});
				this.add(theStoreButtons.get(i));
				this.add(storeName);
			}
		}

		public void paintComponent(Graphics g) {
			g.drawImage((new ImageIcon("dat/map.png")).getImage(), 0, 0, null);
		}
	}

	/*
	 * Class to Handle the Stores Tab
	 */
	private class StoreDisplayPanel extends JPanel {
		private CampusFoodInc thisStore;
		private CheckOutPanel checkoutPanel;
		private FoodDisplayPanel foodDisplayPanel;
		private StoreListPanel storeListPanel;

		public void select(int i) {
			storeListPanel.select(i);
		}

		public void refresh() {
			checkoutPanel.refresh();
		}

		public void addToCart(Food theFood) {
			checkoutPanel.addToCart(theFood);
			thisStore.addToCart(theFood);
		}

		/*
		 * Class to Handle Panel for Displaying Checkout Info
		 */
		private class CheckOutPanel extends JPanel {
			private CartDisplayPanel cartDisplayPanel;
			private SummaryPanel summaryPanel;

			public void refresh() {
				summaryPanel.updateAll();
			}

			public void addToCart(Food theFood) {
				cartDisplayPanel.addToDisplayPanel(theFood);
				summaryPanel.addToSummary(theFood);
			}

			public void reset() {
				cartDisplayPanel.reset();
			}

			private class CartDisplayPanel extends JPanel {
				private JList<Food> cartList;
				private DefaultListModel<Food> cartItems = new DefaultListModel<Food>();

				public boolean isEmpty() {
					return cartItems.isEmpty();
				}

				public void addToDisplayPanel(Food theFood) {
					if (thisStore instanceof VendingMachine)
						reset();
					cartItems.addElement(theFood);
				}

				public void removeSelectedFood() {
					List<Food> theList = cartList.getSelectedValuesList();
					for (int i = 0; i < theList.size(); i++) {
						thisStore.removeFromCart(theList.get(i));
						cartItems.removeElement(theList.get(i));
						summaryPanel.removeFromSummary(theList.get(i));
					}
				}

				public void checkoutCart() {
					try {
						thisStore.buy(thisCard);
					} catch (Exception e) {
						e.printStackTrace();
					}
					reset();
				}

				public void reset() {
					cartItems.removeAllElements();
					summaryPanel.reset();
				}

				public CartDisplayPanel() {
					cartList = new JList<Food>(cartItems);
					cartList.setFixedCellWidth(150);
					cartList.setFixedCellHeight(50);
					cartList.setFont(new Font("Courier", 1, 16));
					this.add(cartList);
				}

			}

			private class SummaryPanel extends JPanel {
				double theCost, theCal, theSug, theSod;
				JLabel costInfo, calInfo, sugInfo, sodInfo;
				JButton checkout, remove;

				public void addToSummary(Food theFood) {
					theCost += theFood.getPrice();
					theCal += theFood.getCal();
					theSug += theFood.getSug();
					theSod += theFood.getSod();
					updateAll();
				}

				public void removeFromSummary(Food theFood) {
					theCost -= theFood.getPrice();
					theCal -= theFood.getCal();
					theSug -= theFood.getSug();
					theSod -= theFood.getSod();
					updateAll();
				}

				public void reset() {
					theCost = theCal = theSug = theSod = 0;
					updateAll();
				}

				public void updateAll() {
					costInfo.setText("Tot : " + theCost + " ("
							+ thisCard.getFund() + ")");
					calInfo.setText("Cal : " + theCal + " ("
							+ thisCard.getCal() + ")");
					sugInfo.setText("Sug : " + theSug + " ("
							+ thisCard.getSug() + ")");
					sodInfo.setText("Sod : " + theSod + " ("
							+ thisCard.getSod() + ")");
					if (cartDisplayPanel.isEmpty()
							|| theCost > thisCard.getFund()
							|| (thisCard.getDietaryProfile().isSet() && (theCal > thisCard
									.getCal() || theSug > thisCard.getSug() || theSod > thisCard
									.getSod())))
						checkout.setEnabled(false);
					else
						checkout.setEnabled(true);
				}

				public SummaryPanel() {
					theCost = theCal = theSug = theSod = 0;
					this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
					costInfo = new JLabel();
					costInfo.setFont(new Font("Courier", 1, 16));
					calInfo = new JLabel();
					calInfo.setFont(new Font("Courier", 1, 16));
					sugInfo = new JLabel();
					sugInfo.setFont(new Font("Courier", 1, 16));
					sodInfo = new JLabel();
					sodInfo.setFont(new Font("Courier", 1, 16));
					checkout = new JButton("Checkout");
					checkout.setFont(new Font("Courier", 1, 16));
					checkout.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cartDisplayPanel.checkoutCart();
						}
					});
					remove = new JButton("Remove Selected");
					remove.setFont(new Font("Courier", 1, 16));
					remove.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cartDisplayPanel.removeSelectedFood();
						}
					});
					this.updateAll();
					this.add(costInfo);
					this.add(calInfo);
					this.add(sugInfo);
					this.add(sodInfo);
					this.add(new JLabel(" "));
					this.add(checkout);
					this.add(new JLabel(" "));
					this.add(remove);
					this.setBorder(new EmptyBorder(5, 5, 50, 25));
				}
			}

			public CheckOutPanel() {
				this.setLayout(new BorderLayout());
				JLabel panelLabel = new JLabel("Cart", SwingConstants.CENTER);
				panelLabel.setFont(new Font("Courier", 1, 20));
				this.add(panelLabel, BorderLayout.NORTH);
				this.add(cartDisplayPanel = new CartDisplayPanel(),
						BorderLayout.CENTER);
				this.add(summaryPanel = new SummaryPanel(), BorderLayout.SOUTH);
			}
		}

		/*
		 * Class to Handle Panel for Displaying Food in the Store
		 */
		private class FoodDisplayPanel extends JPanel {

			public FoodDisplayPanel() {
				this.init();
			}

			public void init() {
				this.removeAll();
				this.setLayout(new GridBagLayout());
				GridBagConstraints gc = new GridBagConstraints();
				int radix = 3;
				gc.gridy = 0;
				for (int i = 0; i < thisStore.getNumFood(); i++) {
					if (i % radix == 0)
						gc.gridy += 2;
					gc.gridx = i % radix;
					Food theFood = thisStore.getFood(i);
					JButton newFood = new JButton((new ImageIcon("dat/food/"
							+ thisStore.getFood(i).getName() + ".png")));
					newFood.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							sp.addToCart(theFood);
						}
					});
					newFood.setBorder(new EmptyBorder(25, 25, 25, 25));
					this.add(newFood, gc);
					gc.gridy++;
					JLabel theFoodLabel = new JLabel("<html>"
							+ theFood.getName() + "<br />$ "
							+ theFood.getPrice() + "</html>");
					theFoodLabel.setFont(new Font("Courier", 1, 16));
					this.add(theFoodLabel, gc);
					gc.gridy--;
				}
			}
		}

		/*
		 * Class to Handle Panel for Displaying List of Available Stores
		 */
		private class StoreListPanel extends JPanel {
			private JList<CampusFoodInc> storeList;
			private DefaultListModel<CampusFoodInc> list = new DefaultListModel<CampusFoodInc>();

			public void select(int i) {
				storeList.setSelectedIndex(i);
			}

			public StoreListPanel() {
				this.setLayout(new BorderLayout());
				JLabel panelLabel = new JLabel("Stores", SwingConstants.CENTER);
				panelLabel.setFont(new Font("Courier", 1, 20));
				this.add(panelLabel, BorderLayout.NORTH);
				CampusFoodInc[] theSet = theMap.getStoreSet();
				for (int i = 0; i < theSet.length; i++)
					list.addElement(theSet[i]);
				storeList = new JList<CampusFoodInc>(list);
				storeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				storeList.setFixedCellWidth(125);
				storeList.setFixedCellHeight(50);
				storeList.setFont(new Font("Courier", 1, 16));
				storeList.setSelectedIndex(0);
				storeList.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						thisStore = ((JList<CampusFoodInc>) e.getSource())
								.getSelectedValue();
						thisStore.reset();
						checkoutPanel.reset();
						foodDisplayPanel.init();
						foodDisplayPanel.transferFocus();
					}

				});
				this.add(storeList, BorderLayout.CENTER);
			}
		}

		/*
		 * Constructor for the Stores Tab
		 */
		public StoreDisplayPanel(CampusFoodInc theStore) {
			this.thisStore = theStore;
			this.setLayout(new BorderLayout());
			this.add(storeListPanel = new StoreListPanel(), BorderLayout.WEST);
			this.add(foodDisplayPanel = new FoodDisplayPanel(),
					BorderLayout.CENTER);
			this.add(checkoutPanel = new CheckOutPanel(), BorderLayout.EAST);
		}
	}

	public SmartCampusGUI() {
		// Window Name
		setTitle("SmartCampus");
		// Set Size
		setSize(900, 750);
		this.setLayout(new FlowLayout());
		JLabel cardInfoLabel = new JLabel("Enter Campus Card ID ");
		JTextField cardInfoField = new JTextField(10);
		JButton cardInfoButton = new JButton("Submit");
		this.add(cardInfoLabel);
		this.add(cardInfoField);
		this.add(cardInfoButton);
		cardInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedReader bf = new BufferedReader(new FileReader(
							"dat/user/" + cardInfoField.getText() + ".txt"));
					String[] userArray = bf.readLine().trim().split(":");
					thisCard = new CampusCard(new PersonalInfo(userArray[0],
							userArray[1]), cardInfoField.getText());
					thisCard.setFund(Double.parseDouble(userArray[2]));
					thisCard.setDietaryProfile(new DietaryProfile(99, 99, 99));
					bf.close();
					init();
				} catch (Exception ex) {
					cardInfoField.setText("Invalid");
				}
			}
		});
		// Set Default Behavior of the Window
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void init() {
		this.getContentPane().removeAll();
		this.setLayout(new BorderLayout());
		this.theMap = new CampusMap();
		tabular.add("Map", mp = new MapDisplayPanel());
		tabular.add("Store", sp = new StoreDisplayPanel(new Cafe()));
		tabular.add("ExpenseProfile", ep = new ExpensePanel());
		tabular.add("DietaryProfile", dp = new DietaryPanel());
		tabular.add("PersonalProfile", pp = new PersonalProfilePanel());
		tabular.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				switch (((JTabbedPane) e.getSource()).getSelectedIndex()) {
				case 1:
					sp.refresh();
					break;
				case 2:
					ep.refresh();
					break;
				case 3:
					dp.refresh();
					break;
				case 4:
					pp.refresh();
					break;
				}
			}
		});
		this.getContentPane().add(tabular, BorderLayout.CENTER);
		setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SmartCampusGUI();
			}
		});
	}

}
