import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Application class that allows the creation of an application object
 * @author kenthowell
 *
 */
public class Application implements ActionListener{
	
	private String name, descrip, org, platform, link, price;
	
	JButton view;
	
	JPanel panel;
	
	/**
	 * Creates an Application object with the input fields
	 * @param name
	 * @param org
	 * @param platform
	 * @param link
	 * @param price
	 * @param descrip
	 */
	public Application(String name, String org, String platform, String link, String price, String descrip) {
		setName(name);
		setDescrip(descrip);
		setOrg(org);
		setPlatform(platform);
		setLink(link);
		setPrice(price);
		setPanel();
	}
	
	/**
	 * Creates an empty Application Object
	 */
	public Application() {
		setName(null);
		setDescrip(null);
		setOrg(null);
		setPlatform(null);
		setLink(null);
		setPrice(null);
	}

	/**
	 * 
	 * @return Name of the Application
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the application
	 * @param name of the application
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return Decsription of the Application
	 */
	public String getDescrip() {
		return descrip;
	}

	/**
	 * Sets the description of the Application
	 * @param descrip Application Description
	 */
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	/**
	 * 
	 * @return Organization that created the Application
	 */
	public String getOrg() {
		return org;
	}

	/**
	 * Sets the orgnization that create the application
	 * @param org organization that created the Application
	 */
	public void setOrg(String org) {
		this.org = org;
	}

	/**
	 * 
	 * @return Application Platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * Sets the Platform that the Application is on
	 * @param platform Application Platform
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/**
	 * 
	 * @return Link to the Application
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets the link to access the Application
	 * @param link to the Application
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 
	 * @return Price of the Application
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Sets the price of the Application
	 * @param price of the Application
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	
	/**
	 * Constructs a panel that every application has
	 */
	public void setPanel() {
		panel = new JPanel();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(700, 550));
		panel.setLayout(new GridLayout(6, 1));
		JLabel nameL = new JLabel("Name: " + getName());
		JLabel descL = new JLabel("Description: " + getDescrip());
		JLabel orgL = new JLabel("Organization: " + getOrg());
		JLabel linkL = new JLabel("Link: " + getLink());
		JLabel priceL = new JLabel("Price: " + getPrice());
		view = new JButton("view");
		view.addActionListener(this);

		nameL.setFont(new Font("Serif", Font.BOLD, 20));

		nameL.setForeground(Color.GRAY);

		panel.add(nameL, 0);
		panel.add(orgL, 1);
		panel.add(linkL, 2);
		panel.add(priceL, 3);
		panel.add(descL, 4);
		panel.add(view, 5);
	}
	
	/**
	 * Returns the panel that every Application has
	 * @return the Application Panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contentEquals("view")) {
			AppGui app = new AppGui();
			app.name.setText("Name: " + getName());
			app.description.setText("Description " + getDescrip());
			app.organization.setText("Organization: " + getOrg());
			app.platforms.setText("Platforms: " + getPlatform());
			app.link.setText("Link:" + getLink());
			app.price.setText("Price: " + getPrice());
			app.setSize(700, 600);
			app.setVisible(true);
		}
	}
	
	
}
