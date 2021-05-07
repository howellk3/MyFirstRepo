import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 * Displays the app request form
 * @author kenthowell
 *
 */
public class AppRequest extends JFrame implements ActionListener {

	JPanel topPanel, centerPanel, bottomPanel;

	JButton add;

	JLabel name, description, organization, platforms, link, price;

	JTextField nameF, organizationF, platformsF, linkF, priceF;

	JTextArea descriptionTA;

	JButton backToHome, submit;

	/**
	 * Creates the needed panels and fields and adds them to the JFrame
	 */
	public AppRequest() {
		name = new JLabel("Enter Name:               ");
		description = new JLabel("Enter Description:      ");
		organization = new JLabel("Enter Organization:    ");
		platforms = new JLabel("Enter Plaftorms:         ");
		link = new JLabel("Enter Link:                 ");
		price = new JLabel("Enter Price:                ");

		nameF = new JTextField(45);
		organizationF = new JTextField(45);
		platformsF = new JTextField(45);
		linkF = new JTextField(45);
		priceF = new JTextField(45);
		descriptionTA = new JTextArea(10, 50);

		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		backToHome = new JButton("Back to Home");
		backToHome.addActionListener(this);
		backToHome.setSize(50, 25);
		topPanel.add(backToHome, BorderLayout.WEST);

		centerPanel = new JPanel();
		centerPanel.add(name);
		centerPanel.add(nameF);
		centerPanel.add(organization);
		centerPanel.add(organizationF);
		centerPanel.add(platforms);
		centerPanel.add(platformsF);
		centerPanel.add(link);
		centerPanel.add(linkF);
		centerPanel.add(price);
		centerPanel.add(priceF);
		centerPanel.add(description);
		centerPanel.add(descriptionTA);

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		submit = new JButton("Submit Request");
		submit.addActionListener(this);
		bottomPanel.add(submit, BorderLayout.WEST);

		this.setLayout(new BorderLayout());

		this.add(topPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		AppRequest req = new AppRequest();
		req.setSize(700, 500);
		req.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		req.setVisible(true);
		req.setTitle("App Addition Request Form");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contentEquals("Back to Home")) {
			GenGui gui = new GenGui();
			gui.setSize(700, 500);
			gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gui.setVisible(true);
			dispose();
		} 
		if (e.getActionCommand().contentEquals("Submit Request")) {
			System.out.println("Hello");
			addAppToFile();
		}
	}

	/**
	 * Adds the app to a file
	 */
	public void addAppToFile() {
		File file = new File("Apps_Tester1.txt");
		try {
			PrintWriter write = new PrintWriter(new FileWriter(file, true));
			write.println(nameF.getText() + ":" + organizationF.getText() + ":" + platformsF.getText() + ":"
					+ linkF.getText() + ":" + priceF.getText() + ":" + descriptionTA.getText());
			write.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		} 
	}
}