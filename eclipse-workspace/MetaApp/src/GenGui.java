import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class GenGui extends JFrame implements ActionListener {

	JPanel bottomPanel, topPanel, centerPanel, topCenter, bottomCenter, scrollPanel;

	JLabel view, search, sort, loggedIn, filter;

	JButton login, lowToHigh, highToLow, aToZ, zeroTofive, fiveToTen, request, reset, viewApp;

	JTextField searchF;

	JTextArea apps;

	JScrollBar scrollBar;
	
	JScrollPane scrollPane;

	String sorterChoice;

	Application[] appArray;

	public GenGui() {

		createLabels(); //Creates the Labels
		
		createButtons(); //Creates Buttons

		appArray = appGrabber(); //Initializes appArray using the appGrabber() function
		
		createScrollPane(); //Creates the JScrollPane

		
		// Below this line is simply adding everything to the proper panels and frame
		request = new JButton("Add Application to Repository");
		request.addActionListener(this);
		request.setVisible(false);

		topPanel = new JPanel();
		topPanel.setSize(100, 250);
		topPanel.add(search);
		topPanel.add(searchF);
		topPanel.add(login, BorderLayout.NORTH);
		topPanel.add(loggedIn);

		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setSize(150, 700);

		topCenter = new JPanel();
		topCenter.setLayout(new GridLayout(1, 5));
		topCenter.setSize(75, 700);
		topCenter.add(sort, 0);
		topCenter.add(reset, 1);
		topCenter.add(aToZ, 2);
		topCenter.add(lowToHigh, 3);
		topCenter.add(highToLow, 4);

		bottomCenter = new JPanel();
		bottomCenter.setLayout(new GridLayout(1, 6));
		bottomCenter.setSize(75, 700);
		bottomCenter.add(filter, 0);
		bottomCenter.add(zeroTofive, 1);
		bottomCenter.add(fiveToTen, 2);

		centerPanel.add(topCenter, BorderLayout.NORTH);
		centerPanel.add(bottomCenter, BorderLayout.SOUTH);

		bottomPanel = new JPanel();
		bottomPanel.setSize(700, 250);
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(view, BorderLayout.NORTH);
		bottomPanel.add(scrollPane, BorderLayout.CENTER);
		bottomPanel.add(request, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		GenGui gui = new GenGui();
		gui.setSize(700, 500);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contentEquals("Login")) {
			LoginDriver log = new LoginDriver();
			log.setSize(300, 200);
			log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			log.setVisible(true);
			dispose();
		} else if (e.getActionCommand().contentEquals("Add Application to Repository")) {
			AppRequest req = new AppRequest();
			req.setSize(700, 500);
			req.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			req.setVisible(true);
			dispose();
		} else if (e.getActionCommand().contentEquals("Alphabetical")) {
			appArray = sorter("Alphabetical", appArray);
			scrollPanel.removeAll();
			for (int i = 0; i < appArray.length; i++) {
				JPanel tempP = makeAppPanel(appArray[i]);
				scrollPanel.add(tempP, i);
			}
			scrollPanel.revalidate();
			scrollPanel.repaint();
		} else if (e.getActionCommand().contentEquals("Price: Increasing")) {
			appArray = sorter("Increasing", appArray);
			scrollPanel.removeAll();
			for (int i = 0; i < appArray.length; i++) {
				JPanel tempP = makeAppPanel(appArray[i]);
				scrollPanel.add(tempP, i);
			}
			scrollPanel.revalidate();
			scrollPanel.repaint();
		} else if (e.getActionCommand().contentEquals("Price: Decreasing")) {
			appArray = sorter("Decreasing", appArray);
			scrollPanel.removeAll();
			for (int i = 0; i < appArray.length; i++) {
				JPanel tempP = makeAppPanel(appArray[i]);
				scrollPanel.add(tempP, i);
			}
			scrollPanel.revalidate();
			scrollPanel.repaint();
		}  else if (e.getActionCommand().contentEquals("$0-$5")) {
			appArray = filter("zeroToFive", appArray);
			scrollPanel.removeAll();
			for (int i = 0; i < appArray.length; i++) {
				JPanel tempP = makeAppPanel(appArray[i]);
				scrollPanel.add(tempP, i);
			}
			scrollPanel.revalidate();
			scrollPanel.repaint();
		}  else if (e.getActionCommand().contentEquals("$5-$10")) {
			appArray = filter("fiveToTen", appArray);
			scrollPanel.removeAll();
			for (int i = 0; i < appArray.length; i++) {
				JPanel tempP = makeAppPanel(appArray[i]);
				scrollPanel.add(tempP, i);
			}
			scrollPanel.revalidate();
			scrollPanel.repaint();
		} else if (e.getActionCommand().contentEquals("Reset Filters/Sorts")) {
			resetter();
		}
	}

	/**
	 * Creates the labels for the frame
	 */
	public void createLabels() {
		view = new JLabel("List of Applications");
		search = new JLabel("Search:");
		sort = new JLabel("Sort By:");
		loggedIn = new JLabel("Not Logged In");
		filter = new JLabel("Filter");
		searchF = new JTextField(25);
	}
	
	/**
	 * Creates the buttons for the frame
	 */
	public void createButtons() {
		login = new JButton("Login");
		login.addActionListener(this);
		lowToHigh = new JButton("Price: Increasing");
		lowToHigh.addActionListener(this);
		highToLow = new JButton("Price: Decreasing");
		highToLow.addActionListener(this);
		aToZ = new JButton("Alphabetical");
		aToZ.addActionListener(this);
		zeroTofive = new JButton("$0-$5");
		zeroTofive.addActionListener(this);
		fiveToTen = new JButton("$5-$10");
		fiveToTen.addActionListener(this);
		reset = new JButton("Reset Filters/Sorts");
		reset.addActionListener(this);
	}
	
	/**
	 * Creates the scroll pane and sets the default view option
	 */
	public void createScrollPane() {
		scrollPanel = new JPanel();
		scrollPanel.setLayout(new GridLayout(appArray.length, 1));
		scrollPanel.setPreferredSize(new Dimension(700, 2000));

		for (int i = 0; i < appArray.length; i++) {
			JPanel tempP = makeAppPanel(appArray[i]);
			scrollPanel.add(tempP, i);
		}

		scrollPane = new JScrollPane(scrollPanel);
		scrollPane.setPreferredSize(new Dimension(700, 350));

		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	/**
	 * Makes a JPanel with Labels that show the information of the Application
	 * 
	 * @param app Application to be displayed
	 * @return JPanel that contains the app information
	 */
	public JPanel makeAppPanel(Application app) {	
		JPanel appP = app.getPanel();
		return appP;
	}

	/**
	 * Looks at a colon delmited file and creates an array of Application objects
	 * 
	 * @return apps An array of Application objects
	 */
	public Application[] appGrabber() {
		int i = 0;
		int lines = lineCounter("Apps_Tester1.txt");
		File file = new File("Apps_Tester1.txt");
		Scanner reader;
		try {
			reader = new Scanner(file);
			Application[] apps = new Application[lines];
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] info = line.split(":");
				apps[i] = new Application(info[0], info[1], info[2], info[3], info[4], info[5]);
				i++;
			}
			reader.close();
			return apps;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Counts the number of lines in a file
	 * 
	 * @param fileName name of the file
	 * @return number of lines in the input file
	 */
	public int lineCounter(String fileName) {
		try {
			Path file = Paths.get(fileName);
			int lines = (int) Files.lines(file).count();
			return lines;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return 0;
	}

	/**
	 * Base sorter to sort the array of applications based on the choice parameter
	 * 
	 * @param choice way that the applications should be sorted
	 * @param apps   array to be sorted
	 * @return sorted array
	 */
	public Application[] sorter(String choice, Application[] apps) {
		if (choice.equals("Alphabetical")) {
			Application[] result = alphabeticalSort(apps);
			return result;
		} else if (choice.equals("Increasing")) {
			Application[] result = increasePriceSort(apps);
			return result;
		} else if (choice.equals("Decreasing")) {
			Application[] result = decreasePriceSort(apps);
			return result;
		} else {
			return null;
		}
	}

	/**
	 * Sorts the array of application objects alphabetically based on the
	 * applications name
	 * 
	 * @param apps array of applications to be sorted
	 * @return sorted array
	 */
	public Application[] alphabeticalSort(Application[] apps) {
		for (int i = 0; i < apps.length; i++) {
			for (int x = i + 1; x < apps.length; x++) {
				if (apps[i].getName().compareTo(apps[x].getName()) > 0) {
					Application temp = apps[i];
					apps[i] = apps[x];
					apps[x] = temp;
				}
			}
		}
		return apps;
	}
	

	/**
	 * Sorts the array of application objects in increasing order based on the price using insertion sort
	 * 
	 * @param apps array to be sorted
	 * @return sorted array
	 */
	public Application[] increasePriceSort(Application[] apps) {
		for (int i = 1; i < apps.length; i++) {
			Application key = apps[i];
			int x = i - 1;

			while (x >= 0 && Integer.parseInt(apps[x].getPrice()) > Integer.parseInt(key.getPrice())) {				
				apps[x + 1] = apps[x];
				x--;
			}
			apps[x + 1] = key;
		}
		return apps;
	}
	
	/**
	 * Uses the increasePriceSort then revereses the array to have the prices be in decereasing order
	 * @param apps array to be sorted
	 * @return sorted array
	 */
	public Application[] decreasePriceSort(Application[] apps) {
		Application[] temp = increasePriceSort(apps);
		Application[] result = new Application[temp.length];
		int count = 0;
		for (int i = temp.length - 1; i >= 0; i--) {
			result[count] = temp[i];
			count++;
		}
		return result;
	}
	
	public Application[] filter(String choice, Application[] apps) {
		Application[] result;
		if (choice.equals("zeroToFive")) {
			result = zeroToFive(apps);
			return result;
		} else if (choice.equals("fiveToTen")) {
			result = fiveToTen(apps);
			return result;
		} else {
			return null;
		}
	}
	
	/**
	 * Filters the input array so that the return array only contains Applications with prices 0-5
	 * @param apps array to be filtered
	 * @return filtered array
	 */
	public Application[] zeroToFive(Application[] apps) {
		ArrayList<Application> temp = new ArrayList<Application>();
		for (int i = 0; i < apps.length; i++) {
			int price = Integer.parseInt(apps[i].getPrice());
			if(price >= 0 && price <= 5) {
				temp.add(apps[i]);
			}
		}
		Application[] result = new Application[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			result[i] = temp.get(i);
		}
		return result;
	}
	
	/**
	 * Filters the input array so that the return array only contains Applications with prices 5-10
	 * @param apps array to be filtered
	 * @return filtered array
	 */
	public Application[] fiveToTen(Application[] apps) {
		ArrayList<Application> temp = new ArrayList<Application>();
		for (int i = 0; i < apps.length; i++) {
			int price = Integer.parseInt(apps[i].getPrice());
			if(price >= 5 && price <= 10) {
				temp.add(apps[i]);
			}
		}
		Application[] result = new Application[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			result[i] = temp.get(i);
		}
		return result;
	}
	
	/**
	 * Resets the viewable applications to all apps from oldest to most recent
	 * @return
	 */
	public void resetter() {
		appArray = appGrabber();
		scrollPanel.removeAll();
		for (int i = 0; i < appArray.length; i++) {
			JPanel tempP = makeAppPanel(appArray[i]);
			scrollPanel.add(tempP, i);
		}
		scrollPanel.revalidate();
		scrollPanel.repaint();
	}

}
