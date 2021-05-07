import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * First page displayed that allows the user to select if they have an account already or not
 * @author kenthowell
 *
 */
public class LoginDriver extends JFrame implements ActionListener{
	
	JPanel panel;
	
	JLabel chooseOption, empty;
	
	JButton haveAccount, makeAccount;

	public static void main(String[] args) {
		LoginDriver yup = new LoginDriver();
		
		yup.setSize(300,250);
		yup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		yup.setVisible(true);
	}
	
	/**
	 * Creates the JPanels and adds them to the JFrame
	 */
	public LoginDriver() {
		chooseOption = new JLabel("Please choose an Option");
		chooseOption.setBounds(75,20,200,25);
		
		haveAccount = new JButton("I already have an account");
		haveAccount.addActionListener(this);
		haveAccount.setBounds(50, 50, 200, 25);
		
		makeAccount = new JButton("I need to make an account");
		makeAccount.addActionListener(this);
		makeAccount.setBounds(50, 80, 200, 25);
		
		empty = new JLabel("");
		
		
		JFrame frame = new JFrame();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		panel.add(chooseOption);
		panel.add(haveAccount);
		panel.add(makeAccount);
		
		this.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contentEquals("I already have an account")) {
			LogInBase base = new LogInBase();
			base.setSize(300,200);
			base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			base.setVisible(true);
			panel.setVisible(false);
			dispose();
			
		} else if (e.getActionCommand().contentEquals("I need to make an account")) {
			CreateNewLogin newLog = new CreateNewLogin();
			newLog.setSize(400,200);
			newLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			newLog.setVisible(true);
			panel.setVisible(false);
			dispose();
		}
	}
}
