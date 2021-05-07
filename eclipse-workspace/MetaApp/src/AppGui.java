import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Displays a JFrame containing information and comments about the Application
 * @author kenthowell
 *
 */
public class AppGui extends JFrame implements ActionListener{
	
	JPanel topPanel, centerPanel, bottomPanel;
	
	JButton login, backToHome;
	
	JTextField searchF;
	
	JLabel name, organization, platforms, link, price, description, search, loggedIn, commentLabel;
	
	JTextArea comments;
	
	/**
	 * Creates the needed Jpanels and fields and adds them to the JFrame
	 */
	public AppGui() {
		
		search = new JLabel("Search: ");
		searchF = new JTextField(25);
		login = new JButton("Login");
		login.addActionListener(this);
		loggedIn = new JLabel("Not Logged In");
		
		name = new JLabel("Name: Example App");
		organization = new JLabel("Organization: Organization");
		platforms = new JLabel("Plaftorms: Example Platform");
		link = new JLabel("Link: Example Link");
		price = new JLabel("Price: Example Price");
		description = new JLabel("Description: Example Description");
		
		
		commentLabel = new JLabel("Comments");
		comments = new JTextArea(20, 50);
		
		backToHome = new JButton("Back to Home");
		backToHome.addActionListener(this);
		
		comments.setText("Test String");
		
		JScrollPane scrollPane = new JScrollPane(comments);
		
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		topPanel = new JPanel();
		topPanel.setSize(100,250);
		
		topPanel.add(backToHome);
		topPanel.add(search);
		topPanel.add(searchF);
		
		topPanel.add(login,BorderLayout.NORTH);
		topPanel.add(loggedIn);
		
		centerPanel = new JPanel();
		centerPanel.setSize(150, 700);
		centerPanel.setLayout(new GridLayout(7, 1));
		
		centerPanel.add(name, 0);
		centerPanel.add(organization, 1);
		centerPanel.add(platforms, 2);
		centerPanel.add(link, 3);
		centerPanel.add(price, 4);
		centerPanel.add(description, 5);
		JLabel empty = new JLabel("");
		centerPanel.add(empty, 6);
		
		bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(700, 400));
		bottomPanel.setLayout(new BorderLayout());
		
		bottomPanel.add(commentLabel, BorderLayout.CENTER);
		
		bottomPanel.add(scrollPane, BorderLayout.SOUTH);
		
		
		
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		AppGui app = new AppGui();
		app.setSize(700, 700);
		app.setTitle("Example Application Page");
		app.setVisible(true);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contentEquals("Login")) {
			LoginDriver log = new LoginDriver();
			log.setSize(300, 200);
			log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			log.setVisible(true);
		} else if (e.getActionCommand().contentEquals("Back to Home")) {
			GenGui gui = new GenGui();
			gui.setSize(700,500);
			gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gui.setVisible(true);
			dispose();
		}
		
	}

}
