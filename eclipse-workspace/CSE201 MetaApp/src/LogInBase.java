import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class LogInBase extends JFrame implements ActionListener{
	
	JLabel username, password, succesfulLogin, newUser, newPass;
	JTextField usernameTF, newUserTF;
	JPasswordField passwordTF, newPassTF;
	JButton login, createNew;

	public static void main(String[] args) {
		LogInBase yup = new LogInBase();
		
		yup.setSize(300,250);
		yup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		yup.setVisible(true);
	}
	
	public LogInBase() {
		username = new JLabel("Username");
		username.setBounds(10,20,80,25);
		usernameTF = new JTextField(25);
		usernameTF.setBounds(100,20,165,25);
		
		password = new JLabel("Password");
		password.setBounds(10,50,80,25);
		passwordTF = new JPasswordField(25);
		passwordTF.setBounds(100,50,165,25);
		
		login = new JButton("Login");
		login.addActionListener(this);
		login.setBounds(10, 80, 100, 25);
		
		succesfulLogin = new JLabel("");
		succesfulLogin.setBounds(10, 110, 250, 25);
		
		
		
		
		JFrame frame = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		panel.add(username);
		panel.add(password);
		panel.add(usernameTF);
		panel.add(passwordTF);
		panel.add(login);
		panel.add(succesfulLogin);
		
		this.add(panel);
	}
	
	public boolean loginChecker(String username, String password) {
		Scanner reader;
		try {
			reader = new Scanner(new File("user_pass.txt"));
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] info = line.split(",");
				if (info[0].equals(username) && info[1].equals(password)) {
					return true;
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String userText = usernameTF.getText();
		String passText = passwordTF.getText();
		
		if (e.getActionCommand().contentEquals("Login")) {
			if (loginChecker(userText, passText)) {
			succesfulLogin.setText("Logged In");
			} else {
				succesfulLogin.setText("User or pass did not match an account");
			}
		}
	}

}
