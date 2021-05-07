import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import java.util.*;

/**
 * Allows the user to create a new login
 * @author kenthowell
 *
 */
public class CreateNewLogin extends JFrame implements ActionListener {

	JPanel createPanel;
	JLabel succesfulLogin, newUser, newPass, saved;
	JTextField newUserTF, newPassTF;
	JButton saveLogin, proceedToLogin;

	/**
	 * Creates the needed JPanels and Fields and adds them to the JFrame object
	 */
	public CreateNewLogin() {
		newUser = new JLabel("Please enter a Username");
		newUser.setBounds(10, 20, 175, 25);
		newUserTF = new JTextField();
		newUserTF.setBounds(190, 20, 200, 25);

		newPass = new JLabel("Please enter a Password");
		newPass.setBounds(10, 50, 175, 25);
		newPassTF = new JTextField();
		newPassTF.setBounds(190, 50, 200, 25);

		saveLogin = new JButton("Save");
		saveLogin.addActionListener(this);
		saveLogin.setBounds(10, 80, 100, 25);
		
		saved = new JLabel("");
		saved.setBounds(10, 110, 200, 25);
		
		proceedToLogin = new JButton("Procced to Login");
		proceedToLogin.addActionListener(this);
		proceedToLogin.setBounds(10, 140, 175, 25);
		proceedToLogin.setVisible(false);

		JFrame createFrame = new JFrame();

		createPanel = new JPanel();
		createPanel.setLayout(null);

		createPanel.add(newUser);
		createPanel.add(newUserTF);

		createPanel.add(newPass);
		createPanel.add(newPassTF);
		createPanel.add(saveLogin);
		createPanel.add(saved);
		createPanel.add(proceedToLogin);

		this.add(createPanel);
	}
	
	/**
	 * Checks to see if the username input matches an existing username
	 * @param username users desired username
	 * @return returns true if the username already exists and false otherwise
	 */
	public boolean userExists(String username) {
		Scanner reader;

		try {
			reader = new Scanner(new File("user_pass.txt"));
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] info = line.split(",");
				if (info[0].equals(username)) {
					return true;
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Writes the new account to the username_password file
	 * @param password users password
	 * @param username users username
	 * @return
	 */
	public boolean writeToFile(String password, String username) {
		PrintWriter writer;
		if (userExists(username)) {
			return false;
		} else {
			try {
				File out = new File("user_pass.txt");
				writer = new PrintWriter(new FileWriter(out, true));
				writer.println(username + "," + password);
				writer.close();
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			return false;
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contentEquals("Save")) {
			String userText = newUserTF.getText();
			String passText = newPassTF.getText();
			boolean infoSaved = writeToFile(passText, userText);
			if (infoSaved) {
				proceedToLogin.setVisible(true);
				saved.setText("Info Saved");
			} else {
				saved.setText("User name already exists");
			}
		} else if (e.getActionCommand().contentEquals("Procced to Login")) {
			LogInBase base = new LogInBase();
			base.setSize(300,200);
			base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			base.setVisible(true);
			dispose();
		}

	}
}

